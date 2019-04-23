package com.lirs.coursework.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ToggleButton
import com.lirs.coursework.R
import com.lirs.coursework.model.PackageManager
import kotlinx.android.synthetic.main.fragment_joints.view.*

class JointsFragment : Fragment(), CompoundButton.OnCheckedChangeListener {

    private lateinit var buttonViewPrevious: CompoundButton
//    private lateinit var buttonViewMediator: CompoundButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_joints, null)
        view.btn_stop.setOnClickListener { PackageManager.stopAll() }

        view.toggle_waist.setOnCheckedChangeListener(this)
        view.toggle_waist_left.setOnCheckedChangeListener(this)
        view.toggle_waist_right.setOnCheckedChangeListener(this)

        view.toggle_shoulder.setOnCheckedChangeListener(this)
        view.toggle_shoulder_up.setOnCheckedChangeListener(this)
        view.toggle_shoulder_down.setOnCheckedChangeListener(this)

        view.toggle_elbow.setOnCheckedChangeListener(this)
        view.toggle_elbow_up.setOnCheckedChangeListener(this)
        view.toggle_elbow_down.setOnCheckedChangeListener(this)

        view.toggle_neck.setOnCheckedChangeListener(this)
        view.toggle_neck_up.setOnCheckedChangeListener(this)
        view.toggle_neck_down.setOnCheckedChangeListener(this)

        view.toggle_gripper.setOnCheckedChangeListener(this)
        view.toggle_gripper_free.setOnCheckedChangeListener(this)
        view.toggle_gripper_grab.setOnCheckedChangeListener(this)

        buttonViewPrevious = ToggleButton(activity)
        return view
    }

    override fun onCheckedChanged(buttonViewCurrent: CompoundButton, isChecked: Boolean) {
        if (isChecked) {
            when (buttonViewCurrent) {
                buttonViewCurrent.toggle_waist -> {
                    view?.toggle_waist_left?.visibility = View.VISIBLE
                    view?.toggle_waist_right?.visibility = View.VISIBLE
                }
                buttonViewCurrent.toggle_shoulder -> {
                    view?.toggle_shoulder_up?.visibility = View.VISIBLE
                    view?.toggle_shoulder_down?.visibility = View.VISIBLE
                }
                buttonViewCurrent.toggle_elbow -> {
                    view?.toggle_elbow_up?.visibility = View.VISIBLE
                    view?.toggle_elbow_down?.visibility = View.VISIBLE
                }
                buttonViewCurrent.toggle_neck -> {
                    view?.toggle_neck_up?.visibility = View.VISIBLE
                    view?.toggle_neck_down?.visibility = View.VISIBLE
                }
                buttonViewCurrent.toggle_gripper -> {
                    view?.toggle_gripper_free?.visibility = View.VISIBLE
                    view?.toggle_gripper_grab?.visibility = View.VISIBLE
                }

                buttonViewCurrent.toggle_waist_left -> {
                    PackageManager.startWaist(false)
                }
                buttonViewCurrent.toggle_waist_right -> {
                    PackageManager.startWaist(true)
                }
                buttonViewCurrent.toggle_shoulder_up -> {
                    PackageManager.startShoulder(true)
                }
                buttonViewCurrent.toggle_shoulder_down -> {
                    PackageManager.startShoulder(false)
                }
                buttonViewCurrent.toggle_elbow_up -> {
                    PackageManager.startElbow(false)
                }
                buttonViewCurrent.toggle_elbow_down -> {
                    PackageManager.startElbow(true)
                }
                buttonViewCurrent.toggle_neck_up -> {
                    PackageManager.startNeck(false)
                }
                buttonViewCurrent.toggle_neck_down -> {
                    PackageManager.startNeck(true)
                }
                buttonViewCurrent.toggle_gripper_free -> {
                    PackageManager.startOpenGripper()
                }
                buttonViewCurrent.toggle_gripper_grab -> {
                    PackageManager.startCloseGripper()
                }
            }
        } else {
            when (buttonViewCurrent) {
                buttonViewCurrent.toggle_gripper -> {
                    view?.toggle_gripper_free?.visibility = View.INVISIBLE
                    view?.toggle_gripper_grab?.visibility = View.INVISIBLE
                }
                buttonViewCurrent.toggle_waist -> {
                    view?.toggle_waist_left?.visibility = View.INVISIBLE
                    view?.toggle_waist_right?.visibility = View.INVISIBLE
                }
                buttonViewCurrent.toggle_shoulder -> {
                    view?.toggle_shoulder_up?.visibility = View.INVISIBLE
                    view?.toggle_shoulder_down?.visibility = View.INVISIBLE
                }
                buttonViewCurrent.toggle_elbow -> {
                    view?.toggle_elbow_up?.visibility = View.INVISIBLE
                    view?.toggle_elbow_down?.visibility = View.INVISIBLE
                }
                buttonViewCurrent.toggle_neck -> {
                    view?.toggle_neck_up?.visibility = View.INVISIBLE
                    view?.toggle_neck_down?.visibility = View.INVISIBLE
                }

                buttonViewCurrent.toggle_waist_left,
                buttonViewCurrent.toggle_waist_right -> {
                    PackageManager.stopWaist()
                }
                buttonViewCurrent.toggle_shoulder_up,
                buttonViewCurrent.toggle_shoulder_down -> {
                    PackageManager.stopShoulder()
                }
                buttonViewCurrent.toggle_elbow_up,
                buttonViewCurrent.toggle_elbow_down -> {
                    PackageManager.stopElbow()
                }
                buttonViewCurrent.toggle_neck_up,
                buttonViewCurrent.toggle_neck_down -> {
                    PackageManager.stopNeck()
                }
                buttonViewCurrent.toggle_gripper_free -> {
                    PackageManager.stopOpenGripper()
                }
                buttonViewCurrent.toggle_gripper_grab -> {
                    PackageManager.stopCloseGripper()
                }
            }
        }
        /*buttonViewPrevious.isChecked = false
        buttonViewPrevious = buttonViewCurrent*/
    }
}
