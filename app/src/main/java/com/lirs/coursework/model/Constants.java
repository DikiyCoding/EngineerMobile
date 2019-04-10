package com.lirs.coursework.model;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

class Constants {

    public static InetAddress ADDRESS_CLIENT;
    public static InetAddress ADDRESS_SERVER;

    public static DatagramSocket SOCKET_CLIENT;

    public static final short PORT_CLIENT = 10001;
    public static final short PORT_SERVER = 10000;

    public static final String IP_CLIENT = "10.42.0.13";
    public static final String IP_SERVER = "10.42.0.1";

    static {
        try {
            ADDRESS_CLIENT = InetAddress.getByName(IP_CLIENT);
            ADDRESS_SERVER = InetAddress.getByName(IP_SERVER);
            SOCKET_CLIENT = new DatagramSocket(PORT_CLIENT, ADDRESS_CLIENT);
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }

    public static final int REMOTE_CONTROL_PACKET_ID = 1;
    public static final int REMOTE_CONTROL_PACKET_SIZE = 57;
    public static final int FRAME_TYPE_POSITION = 0;
}
