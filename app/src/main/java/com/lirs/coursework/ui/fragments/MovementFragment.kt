package com.lirs.coursework.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.MediaController
import com.lirs.coursework.R
import com.lirs.coursework.model.PackageManager
import kotlinx.android.synthetic.main.fragment_movement.view.*

class MovementFragment : Fragment(), CompoundButton.OnCheckedChangeListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movement, null)
        view.btn_stop.setOnClickListener { PackageManager.stopAll() }
        view.toggle_move_up.setOnCheckedChangeListener(this)
        view.toggle_move_down.setOnCheckedChangeListener(this)
        view.toggle_move_right.setOnCheckedChangeListener(this)
        view.toggle_move_left.setOnCheckedChangeListener(this)
        view.toggle_flippers.setOnCheckedChangeListener(this)
        view.toggle_flippers_up.setOnCheckedChangeListener(this)
        view.toggle_flippers_down.setOnCheckedChangeListener(this)
//        view.video.
        /*val mediaController = MediaController(activity)
        mediaController.setAnchorView(view.video)
        mediaController.setMediaPlayer(view.video)
        view.video.setMediaController(mediaController)
        view.video.setVideoURI(Uri.parse("rtsp://10.42.0.1:8554/zoom"))
        view.video.setOnPreparedListener {
            view.video.requestFocus()
            view.video.start()
        }*/
        return view
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mediaURL = "rtsp://10.42.0.1:8554/zoom"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mediaURL))
        startActivity(intent)
    }*/

    override fun onCheckedChanged(buttonViewCurrent: CompoundButton, isChecked: Boolean) {
        if (isChecked) {
            when (buttonViewCurrent) {
                buttonViewCurrent.toggle_move_up -> {
                    PackageManager.startMove(false)
                }
                buttonViewCurrent.toggle_move_down -> {
                    PackageManager.startMove(true)
                }
                buttonViewCurrent.toggle_move_right -> {
                    PackageManager.startRotate(true)
                }
                buttonViewCurrent.toggle_move_left -> {
                    PackageManager.startRotate(false)
                }
                buttonViewCurrent.toggle_flippers_up -> {
                    PackageManager.startFlippersUp()
                }
                buttonViewCurrent.toggle_flippers_down -> {
                    PackageManager.startFlippersDown()
                }
                buttonViewCurrent.toggle_flippers -> {
                    view?.toggle_flippers_up?.visibility = View.VISIBLE
                    view?.toggle_flippers_down?.visibility = View.VISIBLE
                }
            }
        } else {
            when (buttonViewCurrent) {
                buttonViewCurrent.toggle_move_up, buttonViewCurrent.toggle_move_down -> {
                    PackageManager.stopMove()
                }
                buttonViewCurrent.toggle_move_left, buttonViewCurrent.toggle_move_right -> {
                    PackageManager.stopRotate()
                }
                buttonViewCurrent.toggle_flippers_up -> {
                    PackageManager.stopFlippersUp()
                }
                buttonViewCurrent.toggle_flippers_down -> {
                    PackageManager.stopFlippersDown()
                }
                buttonViewCurrent.toggle_flippers -> {
                    view?.toggle_flippers_up?.visibility = View.INVISIBLE
                    view?.toggle_flippers_down?.visibility = View.INVISIBLE
                }
            }
        }
    }
}
