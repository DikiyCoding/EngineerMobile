package com.lirs.coursework.utils

import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import com.lirs.coursework.R
import com.lirs.coursework.model.PackageManager
import kotlinx.android.synthetic.main.nav_drawer_item.view.*

class DrawerHandler {

    private lateinit var menu: Menu

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

    private lateinit var seekBarListener: SeekBar.OnSeekBarChangeListener

    fun handle(menu: Menu) {
        initMenu(menu)
        initViews()
        initListeners()
    }
    
    private fun initMenu(menu: Menu) {
        this.menu = menu
    }

    private fun initViews() {
        initActionViews()
        initTextViews()
        initButtons()
        initSeekBars()
    }

    private fun initListeners() {
        initButtonListeners()
        initSeekBarListeners()
    }

    private fun initActionViews() {
        viewMove = menu.findItem(R.id.item_drawer_nav_movement).actionView
        viewRotate = menu.findItem(R.id.item_drawer_nav_rotation).actionView
        viewNeck = menu.findItem(R.id.item_drawer_nav_neck).actionView
        viewShoulder = menu.findItem(R.id.item_drawer_nav_shoulder).actionView
        viewElbow = menu.findItem(R.id.item_drawer_nav_elbow).actionView
        viewWaist = menu.findItem(R.id.item_drawer_nav_waist).actionView
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

    private inner class SeekBarChangeListener : SeekBar.OnSeekBarChangeListener {

        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            when (seekBar) {
                seekBarMove -> {
                    Log.d("Logs", "Move speed is: $progress")
                    tvMove.text = "$progress"
                    PackageManager.setMoveSpeed(progress)
                }
                seekBarRotate -> {
                    Log.d("Logs", "Rotate speed is: $progress")
                    tvRotate.text = "$progress"
                    PackageManager.setRotateSpeed(progress)
                }
                seekBarNeck -> {
                    Log.d("Logs", "Neck speed is: $progress")
                    tvNeck.text = "$progress"
                    PackageManager.setNeckSpeed(progress)
                }
                seekBarShoulder -> {
                    Log.d("Logs", "Shoulder speed is: $progress")
                    tvShoulder.text = "$progress"
                    PackageManager.setShoulderSpeed(progress)
                }
                seekBarElbow -> {
                    Log.d("Logs", "Elbow speed is: $progress")
                    tvElbow.text = "$progress"
                    PackageManager.setElbowSpeed(progress)
                }
                seekBarWaist -> {
                    Log.d("Logs", "Waist speed is: $progress")
                    tvWaist.text = "$progress"
                    PackageManager.setWaistSpeed(progress)
                }
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}

        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }
}
