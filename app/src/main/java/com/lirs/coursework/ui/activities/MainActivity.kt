package com.lirs.coursework.ui.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import com.lirs.coursework.R
import com.lirs.coursework.ui.fragments.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_drawer_item.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewMove: View
    private lateinit var viewRotate: View
    private lateinit var viewNeck: View
    private lateinit var viewShoulder: View
    private lateinit var viewElbow: View
    private lateinit var viewWaist: View

    private lateinit var tvMove: TextView
    private lateinit var tvRotate: TextView
    private lateinit var tvNeck: TextView
    private lateinit var tvShoulder: TextView
    private lateinit var tvElbow: TextView
    private lateinit var tvWaist: TextView

    private lateinit var btnMoveMax: Button
    private lateinit var btnMoveMin: Button
    private lateinit var btnRotateMax: Button
    private lateinit var btnRotateMin: Button
    private lateinit var btnNeckMax: Button
    private lateinit var btnNeckMin: Button
    private lateinit var btnShoulderMax: Button
    private lateinit var btnShoulderMin: Button
    private lateinit var btnElbowMax: Button
    private lateinit var btnElbowMin: Button
    private lateinit var btnWaistMax: Button
    private lateinit var btnWaistMin: Button

    private lateinit var seekBarMove: SeekBar
    private lateinit var seekBarRotate: SeekBar
    private lateinit var seekBarNeck: SeekBar
    private lateinit var seekBarShoulder: SeekBar
    private lateinit var seekBarElbow: SeekBar
    private lateinit var seekBarWaist: SeekBar

    private lateinit var fragmentJoints: Fragment
    private lateinit var fragmentMovement: Fragment

    private lateinit var seekBarListener: OnSeekBarChangeListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        init()
    }

    override fun onBackPressed() {
        if (drawer_navigation.isDrawerOpen(GravityCompat.START)) {
            drawer_navigation.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun init() {
        initViews()
        initListeners()
    }

    private fun initViews() {
        initContentViews()
        initActionViews()
        initTextViews()
        initButtons()
        initSeekBars()
    }

    private fun initListeners() {
        initButtonListeners()
        initSeekBarListeners()
    }

    private fun initContentViews() {
        val toggle = ActionBarDrawerToggle(
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
                        R.id.item_bottom_nav_joints ->
                            loadFragment(fragmentJoints)
                        R.id.item_bottom_nav_movement ->
                            loadFragment(fragmentMovement)
                    }
                    true
                }

        fragmentJoints = JointsFragment()
        fragmentMovement = MovementFragment()

        loadFragment(fragmentMovement)
    }

    private fun initActionViews() {
        viewMove = navigation.menu.findItem(R.id.item_drawer_nav_movement).actionView
        viewRotate = navigation.menu.findItem(R.id.item_drawer_nav_rotation).actionView
        viewNeck = navigation.menu.findItem(R.id.item_drawer_nav_neck).actionView
        viewShoulder = navigation.menu.findItem(R.id.item_drawer_nav_shoulder).actionView
        viewElbow = navigation.menu.findItem(R.id.item_drawer_nav_elbow).actionView
        viewWaist = navigation.menu.findItem(R.id.item_drawer_nav_waist).actionView
    }

    private fun initTextViews() {
        tvMove = viewMove.tv_value_current
        tvRotate = viewRotate.tv_value_current
        tvNeck = viewNeck.tv_value_current
        tvShoulder = viewShoulder.tv_value_current
        tvElbow = viewElbow.tv_value_current
        tvWaist = viewWaist.tv_value_current
    }

    private fun initButtons() {
        btnMoveMin = viewMove.btn_value_min
        btnMoveMax = viewMove.btn_value_max
        btnRotateMin = viewRotate.btn_value_min
        btnRotateMax = viewRotate.btn_value_max
        btnNeckMin = viewNeck.btn_value_min
        btnNeckMax = viewNeck.btn_value_max
        btnShoulderMin = viewShoulder.btn_value_min
        btnShoulderMax = viewShoulder.btn_value_max
        btnElbowMin = viewElbow.btn_value_min
        btnElbowMax = viewElbow.btn_value_max
        btnWaistMin = viewWaist.btn_value_min
        btnWaistMax = viewWaist.btn_value_max
    }

    private fun initSeekBars() {
        seekBarMove = viewMove.seekBar
        seekBarRotate = viewRotate.seekBar
        seekBarNeck = viewNeck.seekBar
        seekBarShoulder = viewShoulder.seekBar
        seekBarElbow = viewElbow.seekBar
        seekBarWaist = viewWaist.seekBar
    }

    private fun initButtonListeners() {
        btnMoveMin.setOnClickListener { seekBarMove.progress -= 1 }
        btnMoveMax.setOnClickListener { seekBarMove.progress += 1 }
        btnRotateMin.setOnClickListener { seekBarRotate.progress -= 1 }
        btnRotateMax.setOnClickListener { seekBarRotate.progress += 1 }
        btnNeckMin.setOnClickListener { seekBarNeck.progress -= 1 }
        btnNeckMax.setOnClickListener { seekBarNeck.progress += 1 }
        btnShoulderMin.setOnClickListener { seekBarShoulder.progress -= 1 }
        btnShoulderMax.setOnClickListener { seekBarShoulder.progress += 1 }
        btnElbowMin.setOnClickListener { seekBarElbow.progress -= 1 }
        btnElbowMax.setOnClickListener { seekBarElbow.progress += 1 }
        btnWaistMin.setOnClickListener { seekBarWaist.progress -= 1 }
        btnWaistMax.setOnClickListener { seekBarWaist.progress += 1 }
    }

    private fun initSeekBarListeners() {
        seekBarListener = SeekBarChangeListener()
        seekBarMove.setOnSeekBarChangeListener(seekBarListener)
        seekBarRotate.setOnSeekBarChangeListener(seekBarListener)
        seekBarNeck.setOnSeekBarChangeListener(seekBarListener)
        seekBarShoulder.setOnSeekBarChangeListener(seekBarListener)
        seekBarElbow.setOnSeekBarChangeListener(seekBarListener)
        seekBarWaist.setOnSeekBarChangeListener(seekBarListener)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit()
    }

    private inner class SeekBarChangeListener : OnSeekBarChangeListener {

        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            when (seekBar) {
                seekBarMove -> {
                    Log.d("Logs", "$progress")
                    tvMove.text = "$progress"
                }
                seekBarRotate -> {
                    Log.d("Logs", "$progress")
                    tvRotate.text = "$progress"
                }
                seekBarNeck -> {
                    Log.d("Logs", "$progress")
                    tvNeck.text = "$progress"
                }
                seekBarShoulder -> {
                    Log.d("Logs", "$progress")
                    tvShoulder.text = "$progress"
                }
                seekBarElbow -> {
                    Log.d("Logs", "$progress")
                    tvElbow.text = "$progress"
                }
                seekBarWaist -> {
                    Log.d("Logs", "$progress")
                    tvWaist.text = "$progress"
                }
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}

        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }
}
