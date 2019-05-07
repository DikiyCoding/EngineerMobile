package com.lirs.coursework.model

import java.net.InetAddress

object Constants {

    /**
     * Speed
     */

    const val SPEED_STEP = 2730
    const val SPEED_DEFAULT: Short = 5460

    /**
     * Network
     */

    const val PORT_CLIENT: Short = 10001
    const val PORT_SERVER: Short = 10000

    const val IP_CLIENT = "10.42.0.13"
    const val IP_SERVER = "10.42.0.1"

    const val REMOTE_CONTROL_PACKET_ID = 1
    const val REMOTE_CONTROL_PACKET_SIZE = 57
    const val FRAME_TYPE_POSITION = 0

    val ADDRESS_CLIENT: InetAddress = InetAddress.getByName(IP_CLIENT)
    val ADDRESS_SERVER: InetAddress = InetAddress.getByName(IP_SERVER)
    /*val SOCKET_CLIENT: DatagramSocket = DatagramSocket(PORT_CLIENT.toInt(), ADDRESS_CLIENT)*/

    /*init {
        try {
            ADDRESS_CLIENT = InetAddress.getByName(IP_CLIENT)
            ADDRESS_SERVER = InetAddress.getByName(IP_SERVER)
            SOCKET_CLIENT = DatagramSocket(PORT_CLIENT.toInt(), ADDRESS_CLIENT)
        } catch (e: UnknownHostException) {
            e.printStackTrace()
        } catch (e: SocketException) {
            e.printStackTrace()
        }
    }*/
}
