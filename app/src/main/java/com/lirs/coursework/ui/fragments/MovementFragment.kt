package com.lirs.coursework.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.lirs.coursework.R
import com.lirs.coursework.model.PackageManager
import kotlinx.android.synthetic.main.fragment_movement.view.*

class MovementFragment : Fragment(), View.OnTouchListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movement, null)
        view.btn_stop.setOnClickListener {
            Log.d("Logs", "Stop button is clicked")
            PackageManager.stopAll()
        }
        view.btn_up.setOnTouchListener(this)
        view.btn_down.setOnTouchListener(this)
        return view
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            when (view) {
                view.btn_up -> PackageManager.startMove(false)
                view.btn_down -> PackageManager.startMove(true)
            }
        } else if (event.action == MotionEvent.ACTION_UP) {
            when (view) {
                view.btn_up -> PackageManager.stopMove()
                view.btn_down -> PackageManager.stopMove()
            }
        }
        return false
    }
}
