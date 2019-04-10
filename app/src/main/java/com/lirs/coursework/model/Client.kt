package com.lirs.coursework.model

import java.io.IOException
import java.net.DatagramPacket

class Client(sendData: ByteArray) : Runnable {

    private val PACKET_CLIENT: DatagramPacket =
            DatagramPacket(
                    sendData,
                    sendData.size,
                    Constants.ADDRESS_SERVER,
                    Constants.PORT_SERVER.toInt())

    override fun run() {
        try {
            while (true) {
                Constants.SOCKET_CLIENT.send(PACKET_CLIENT)
                println("Great")
                Thread.sleep(200)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
