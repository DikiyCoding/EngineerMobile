package com.lirs.coursework.utils.application

import android.app.Application
import android.content.Context
import com.lirs.coursework.utils.listeners.OneActionCheckedChangeListener
import com.lirs.coursework.utils.listeners.OneDiscloseCheckedChangeListener

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        actionListener = OneActionCheckedChangeListener()
        discloseListener = OneDiscloseCheckedChangeListener()
    }

    companion object {
        lateinit var actionListener: OneActionCheckedChangeListener
        lateinit var discloseListener: OneDiscloseCheckedChangeListener
        lateinit var context: Context
    }
}
