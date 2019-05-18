package com.lirs.coursework.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lirs.coursework.R
import com.lirs.coursework.utils.application.App.Companion.actionListener
import com.lirs.coursework.utils.application.App.Companion.discloseListener
import kotlinx.android.synthetic.main.fragment_joints.view.*

class JointsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_joints, null)
        discloseListener.joints = view
        view.btn_stop.setOnClickListener(actionListener)
        view.toggle_waist.setOnCheckedChangeListener(discloseListener)
        view.toggle_waist_left.setOnCheckedChangeListener(actionListener)
        view.toggle_waist_right.setOnCheckedChangeListener(actionListener)
        view.toggle_shoulder.setOnCheckedChangeListener(discloseListener)
        view.toggle_shoulder_up.setOnCheckedChangeListener(actionListener)
        view.toggle_shoulder_down.setOnCheckedChangeListener(actionListener)
        view.toggle_elbow.setOnCheckedChangeListener(discloseListener)
        view.toggle_elbow_up.setOnCheckedChangeListener(actionListener)
        view.toggle_elbow_down.setOnCheckedChangeListener(actionListener)
        view.toggle_neck.setOnCheckedChangeListener(discloseListener)
        view.toggle_neck_up.setOnCheckedChangeListener(actionListener)
        view.toggle_neck_down.setOnCheckedChangeListener(actionListener)
        view.toggle_gripper.setOnCheckedChangeListener(discloseListener)
        view.toggle_gripper_free.setOnCheckedChangeListener(actionListener)
        view.toggle_gripper_grab.setOnCheckedChangeListener(actionListener)
        return view
    }
}
