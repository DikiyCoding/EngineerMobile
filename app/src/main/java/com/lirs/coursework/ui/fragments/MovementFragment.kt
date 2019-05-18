package com.lirs.coursework.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lirs.coursework.R
import com.lirs.coursework.model.PackageManager
import com.lirs.coursework.utils.application.App.Companion.actionListener
import com.lirs.coursework.utils.application.App.Companion.discloseListener
import kotlinx.android.synthetic.main.fragment_movement.view.*

class MovementFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movement, null)
        discloseListener.movement = view
        view.btn_stop.setOnClickListener(actionListener)
        view.toggle_move_up.setOnCheckedChangeListener(actionListener)
        view.toggle_move_down.setOnCheckedChangeListener(actionListener)
        view.toggle_move_right.setOnCheckedChangeListener(actionListener)
        view.toggle_move_left.setOnCheckedChangeListener(actionListener)
        view.toggle_flippers.setOnCheckedChangeListener(discloseListener)
        view.toggle_flippers_up.setOnCheckedChangeListener(actionListener)
        view.toggle_flippers_down.setOnCheckedChangeListener(actionListener)
        view.toggle_torch.setOnCheckedChangeListener { toggleCurrent, isChecked ->
            if(isChecked)
                PackageManager.turnOnTorch()
            else
                PackageManager.turnOffTorch()
        }
        return view
    }
}
