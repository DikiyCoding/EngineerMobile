package com.lirs.coursework.ui.activities

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.wifi.WifiInfo
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.lirs.coursework.model.Client
import com.lirs.coursework.model.ClientService
import com.lirs.coursework.ui.fragments.ConnectionDialogFragment
import com.lirs.coursework.ui.fragments.InfoFragment
import com.lirs.coursework.ui.fragments.JointsFragment
import com.lirs.coursework.ui.fragments.MovementFragment
import com.lirs.coursework.utils.other.DrawerHandler
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.parceler.Parcels
import veg.mediaplayer.sdk.MediaPlayer
import veg.mediaplayer.sdk.MediaPlayerConfig
import java.nio.ByteBuffer
import android.net.wifi.WifiManager
import android.util.Log
import com.lirs.coursework.R
import com.lirs.coursework.model.Constants.ADDRESS_CLIENT_DHCP
import com.lirs.coursework.utils.other.InetAddressConverter
import com.lirs.coursework.utils.permissions.PermissionCallback
import com.lirs.coursework.utils.permissions.PermissionCheckerUtils
import com.lirs.coursework.utils.permissions.PermissionCheckerUtils.RuntimePermissions.PERMISSION_REQUEST_ACCESS_WIFI
import java.net.InetAddress

class MainActivity : AppCompatActivity(), MediaPlayer.MediaPlayerCallback, PermissionCallback {

    private lateinit var serviceIntent: Intent
    private lateinit var fragmentInfo: Fragment
    private lateinit var fragmentJoints: Fragment
    private lateinit var fragmentMovement: Fragment
    private lateinit var drawerHandler: DrawerHandler
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var mediaConfig: MediaPlayerConfig
    private lateinit var connectionDialog: DialogFragment
    private lateinit var wifiManager: WifiManager
    private lateinit var wifiInfo: WifiInfo
    private lateinit var permissionChecker: PermissionCheckerUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        init()

        if(checkConnection()) bind()
        else showDialog()
    }

    override fun onRestart() {
        super.onRestart()
        if(checkConnection()) bind()
        Log.d("Logs", "Activity is restarted")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Logs", "Activity is started")
    }

    override fun onResume() {
        super.onResume()
        //Если сервис уже запущен, то ничего не делать
        Log.d("Logs", "Activity is resumed")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Logs", "Activity is paused")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Logs", "Activity is stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        unbind()
        Log.d("Logs", "Activity is destroyed")
    }

    override fun permissionGranted(permission: PermissionCheckerUtils.RuntimePermissions) {
        Log.i("Logs", "Разрешения получены")
        if(checkConnection()) bind()
        else showDialog()
    }

    override fun permissionDenied(permission: PermissionCheckerUtils.RuntimePermissions) {
        Log.i("Logs", "Разрешения отклонены")
    }

    override fun onBackPressed() {
        if (drawer_navigation.isDrawerOpen(GravityCompat.START)) {
            drawer_navigation.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.connection -> showDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
            if (requestCode == PERMISSION_REQUEST_ACCESS_WIFI.VALUE)
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    permissionGranted(PERMISSION_REQUEST_ACCESS_WIFI)
                else
                    permissionDenied(PERMISSION_REQUEST_ACCESS_WIFI)
    }

    override fun OnReceiveData(p0: ByteBuffer, p1: Int, p2: Long): Int = 0

    override fun Status(p0: Int): Int = 0

    private fun init() {
        initContentViews()
        initTools()
    }

    private fun initTools() {
        initService()
        initMediaPlayer()
        initPermissionChecker()
    }

    private fun initService() {
        serviceIntent = Intent(this, ClientService::class.java)
        serviceIntent.putExtra("client", Parcels.wrap(Client()))
    }

    private fun initMediaPlayer() {
        mediaConfig = MediaPlayerConfig()
        mediaConfig.connectionUrl = "rtsp://10.42.0.1:8554/zoom"
        mediaConfig.decodingType = 0
    }

    private fun initPermissionChecker() {
        permissionChecker = PermissionCheckerUtils()
    }

    private fun initContentViews() {
        initDrawerViews()
        initDrawerActionBar()
        initBottomNavigation()
        initFragments()
        initDialogs()
    }

    private fun initDrawerViews() {
        drawerHandler = DrawerHandler()
        drawerHandler.handle(navigation.menu)
    }

    private fun initDrawerActionBar() {
        toggle = ActionBarDrawerToggle(
            this,
            drawer_navigation,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)
        drawer_navigation.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun initBottomNavigation() =
        bottom_navigation
            .setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.item_bottom_nav_joints -> {
                        loadFragment(fragmentJoints)
                        video.visibility = View.VISIBLE
                    }
                    R.id.item_bottom_nav_movement -> {
                        loadFragment(fragmentMovement)
                        video.visibility = View.VISIBLE
                    }
                    R.id.item_bottom_nav_info -> {
                        video.visibility = View.GONE
                        loadFragment(fragmentInfo)
                    }
                }
                true
            }

    private fun initDialogs() {
        connectionDialog = ConnectionDialogFragment()
    }

    private fun initFragments() {
        fragmentInfo = InfoFragment()
        fragmentJoints = JointsFragment()
        fragmentMovement = MovementFragment()
        loadFragment(fragmentMovement)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, fragment)
            .commit()
    }

    private fun bind() {
        Log.d("Logs", "connection is using")
        startService(serviceIntent)
        video.Open(mediaConfig, this)
        video.Play()
    }

    private fun unbind() {
        stopService(serviceIntent)
        video.Close()
        video.onDestroy()
    }

    private fun showDialog() =
        connectionDialog.show(supportFragmentManager, "connection")

    private fun checkConnection(): Boolean {
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if (wifiManager.isWifiEnabled) {
            wifiInfo = wifiManager.connectionInfo
            if (wifiInfo.networkId != -1) {
                val inetConverter = InetAddressConverter()
                val ipString= inetConverter.fromIntToString(wifiInfo.ipAddress)
                ADDRESS_CLIENT_DHCP = InetAddress.getByName(ipString)
                Log.d("Logs", wifiInfo.ssid)
                Log.d("Logs", ipString)
                return true
            }
        }
        return false
    }

    private fun checkPermissions() =
        permissionChecker.checkForPermissions(
            this,
            PERMISSION_REQUEST_ACCESS_WIFI,
            this
        )
}
