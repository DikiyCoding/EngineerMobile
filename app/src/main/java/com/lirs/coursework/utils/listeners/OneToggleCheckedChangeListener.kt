package com.lirs.coursework.utils.listeners

import android.view.View
import android.widget.CompoundButton
import android.widget.ToggleButton
import com.lirs.coursework.model.PackageManager
import com.lirs.coursework.utils.application.App

abstract class OneToggleCheckedChangeListener : CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private var togglePrevious: CompoundButton = ToggleButton(App.context)

    override fun onCheckedChanged(toggleCurrent: CompoundButton, isChecked: Boolean) {
        if (isChecked) {
            if (togglePrevious != toggleCurrent && togglePrevious.isChecked) {
                togglePrevious.isChecked = false
                actionSwitchOff(togglePrevious)
            }
            actionSwitchOn(toggleCurrent)
            togglePrevious = toggleCurrent
        } else {
            actionSwitchOff(toggleCurrent)
        }
    }

    abstract fun actionSwitchOn(toggle: CompoundButton)

    abstract fun actionSwitchOff(toggle: CompoundButton)

    override fun onClick(view: View) {
        togglePrevious.isChecked = false
        PackageManager.stopAll()
    }
}
