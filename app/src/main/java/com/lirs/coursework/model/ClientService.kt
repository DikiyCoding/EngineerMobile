package com.lirs.coursework.model

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ClientService(private val client: Client) : Service() {

    lateinit var executor: ExecutorService

    override fun onCreate() {
        super.onCreate()
        executor = Executors.newFixedThreadPool(1)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        executor.execute(client)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
