package com.lirs.coursework.utils.listeners

import android.widget.CompoundButton
import com.lirs.coursework.model.PackageManager
import kotlinx.android.synthetic.main.fragment_joints.view.*
import kotlinx.android.synthetic.main.fragment_movement.view.*

class OneActionCheckedChangeListener : OneToggleCheckedChangeListener() {

    override fun actionSwitchOn(toggle: CompoundButton) {
        when (toggle) {
            toggle.toggle_move_up -> PackageManager.startMove(false)
            toggle.toggle_move_down -> PackageManager.startMove(true)
            toggle.toggle_move_right -> PackageManager.startRotate(true)
            toggle.toggle_move_left -> PackageManager.startRotate(false)
            toggle.toggle_waist_left -> PackageManager.startWaist(false)
            toggle.toggle_waist_right -> PackageManager.startWaist(true)
            toggle.toggle_shoulder_up -> PackageManager.startShoulder(true)
            toggle.toggle_shoulder_down -> PackageManager.startShoulder(false)
            toggle.toggle_elbow_up -> PackageManager.startElbow(false)
            toggle.toggle_elbow_down -> PackageManager.startElbow(true)
            toggle.toggle_neck_up -> PackageManager.startNeck(false)
            toggle.toggle_neck_down -> PackageManager.startNeck(true)
            toggle.toggle_gripper_free -> PackageManager.startOpenGripper()
            toggle.toggle_gripper_grab -> PackageManager.startCloseGripper()
            toggle.toggle_flippers_up -> PackageManager.startFlippersUp()
            toggle.toggle_flippers_down -> PackageManager.startFlippersDown()
        }
    }

    override fun actionSwitchOff(toggle: CompoundButton) {
        when (toggle) {
            toggle.toggle_move_up, toggle.toggle_move_down -> PackageManager.stopMove()
            toggle.toggle_move_left, toggle.toggle_move_right -> PackageManager.stopRotate()
            toggle.toggle_waist_left, toggle.toggle_waist_right -> PackageManager.stopWaist()
            toggle.toggle_shoulder_up, toggle.toggle_shoulder_down -> PackageManager.stopShoulder()
            toggle.toggle_elbow_up, toggle.toggle_elbow_down -> PackageManager.stopElbow()
            toggle.toggle_neck_up, toggle.toggle_neck_down -> PackageManager.stopNeck()
            toggle.toggle_gripper_free -> PackageManager.stopOpenGripper()
            toggle.toggle_gripper_grab -> PackageManager.stopCloseGripper()
            toggle.toggle_flippers_up -> PackageManager.stopFlippersUp()
            toggle.toggle_flippers_down -> PackageManager.stopFlippersDown()
        }
    }
}
