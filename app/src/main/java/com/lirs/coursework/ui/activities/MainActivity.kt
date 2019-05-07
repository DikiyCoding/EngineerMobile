package com.lirs.coursework.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.lirs.coursework.R
import com.lirs.coursework.model.Client
import com.lirs.coursework.model.ClientService
import com.lirs.coursework.ui.fragments.InfoFragment
import com.lirs.coursework.ui.fragments.JointsFragment
import com.lirs.coursework.ui.fragments.MovementFragment
import com.lirs.coursework.utils.DrawerHandler
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.parceler.Parcels
import veg.mediaplayer.sdk.MediaPlayer
import veg.mediaplayer.sdk.MediaPlayerConfig
import java.nio.ByteBuffer

class MainActivity : AppCompatActivity(), MediaPlayer.MediaPlayerCallback {

    private lateinit var serviceIntent: Intent
    private lateinit var fragmentInfo: Fragment
    private lateinit var fragmentJoints: Fragment
    private lateinit var fragmentMovement: Fragment
    private lateinit var drawerHandler: DrawerHandler
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        init()
    }

    override fun OnReceiveData(p0: ByteBuffer, p1: Int, p2: Long): Int {
        return 0
    }

    override fun Status(p0: Int): Int {
        return 0
    }

    override fun onDestroy() {
        video.Close()
        video.onDestroy()
        stopService(serviceIntent)
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (drawer_navigation.isDrawerOpen(GravityCompat.START)) {
            drawer_navigation.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun init() {
        initContentViews()
        initTools()
    }

    private fun initTools() {
        serviceIntent = Intent(this, ClientService::class.java)
        serviceIntent.putExtra("client", Parcels.wrap(Client()))
        startService(serviceIntent)
    }

    private fun initContentViews() {
        drawerHandler = DrawerHandler()
        drawerHandler.handle(navigation.menu)

        toggle = ActionBarDrawerToggle(
                this,
                drawer_navigation,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer_navigation.addDrawerListener(toggle)
        toggle.syncState()

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

        fragmentInfo = InfoFragment()
        fragmentJoints = JointsFragment()
        fragmentMovement = MovementFragment()

        loadFragment(fragmentMovement)

        val config = MediaPlayerConfig()
        config.connectionUrl = "rtsp://10.42.0.1:8554/zoom"
        config.decodingType = 0

        video.Open(config, this)
        video.Play()
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit()
    }
}
