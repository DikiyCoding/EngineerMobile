package com.lirs.coursework.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lirs.coursework.R
import kotlinx.android.synthetic.main.fragment_connection.view.*

class ConnectionDialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_connection, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btn_connect.setOnClickListener {
            startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
        }
    }

    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(
            resources.getDimensionPixelSize(R.dimen.dialog_connection_width),
            resources.getDimensionPixelSize(R.dimen.dialog_connection_height)
        )
        dialog.window?.setBackgroundDrawableResource(R.color.color_black_translucent)
    }
}
