package com.lirs.coursework.utils.listeners

import android.view.View
import android.widget.CompoundButton
import kotlinx.android.synthetic.main.fragment_joints.view.*
import kotlinx.android.synthetic.main.fragment_movement.view.*

class OneDiscloseCheckedChangeListener : OneToggleCheckedChangeListener() {

    lateinit var joints: View
    lateinit var movement: View

    override fun actionSwitchOn(toggle: CompoundButton) {
        when (toggle) {
            toggle.toggle_waist -> {
                joints.toggle_waist_left.visibility = View.VISIBLE
                joints.toggle_waist_right.visibility = View.VISIBLE
            }
            toggle.toggle_shoulder -> {
                joints.toggle_shoulder_up.visibility = View.VISIBLE
                joints.toggle_shoulder_down.visibility = View.VISIBLE
            }
            toggle.toggle_elbow -> {
                joints.toggle_elbow_up.visibility = View.VISIBLE
                joints.toggle_elbow_down.visibility = View.VISIBLE
            }
            toggle.toggle_neck -> {
                joints.toggle_neck_up.visibility = View.VISIBLE
                joints.toggle_neck_down.visibility = View.VISIBLE
            }
            toggle.toggle_gripper -> {
                joints.toggle_gripper_free.visibility = View.VISIBLE
                joints.toggle_gripper_grab.visibility = View.VISIBLE
            }
            toggle.toggle_flippers -> {
                movement.toggle_flippers_up.visibility = View.VISIBLE
                movement.toggle_flippers_down.visibility = View.VISIBLE
            }
        }
    }

    override fun actionSwitchOff(toggle: CompoundButton) {
        when (toggle) {
            toggle.toggle_waist -> {
                joints.toggle_waist_left.visibility = View.INVISIBLE
                joints.toggle_waist_right.visibility = View.INVISIBLE
            }
            toggle.toggle_shoulder -> {
                joints.toggle_shoulder_up.visibility = View.INVISIBLE
                joints.toggle_shoulder_down.visibility = View.INVISIBLE
            }
            toggle.toggle_elbow -> {
                joints.toggle_elbow_up.visibility = View.INVISIBLE
                joints.toggle_elbow_down.visibility = View.INVISIBLE
            }
            toggle.toggle_neck -> {
                joints.toggle_neck_up.visibility = View.INVISIBLE
                joints.toggle_neck_down.visibility = View.INVISIBLE
            }
            toggle.toggle_gripper -> {
                joints.toggle_gripper_free.visibility = View.INVISIBLE
                joints.toggle_gripper_grab.visibility = View.INVISIBLE
            }
            toggle.toggle_flippers -> {
                movement.toggle_flippers_up.visibility = View.INVISIBLE
                movement.toggle_flippers_down.visibility = View.INVISIBLE
            }
        }
    }
}
