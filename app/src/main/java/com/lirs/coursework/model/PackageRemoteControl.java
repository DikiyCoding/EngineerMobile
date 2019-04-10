package com.lirs.coursework.model;

public class PackageRemoteControl {

    private static PackageRemoteControl instance;

    private volatile byte[] packet;

    private PackageRemoteControl() {
        packet = createPackage();
    }

    public static PackageRemoteControl getInstance() {
        if (instance == null)
            instance = new PackageRemoteControl();
        return instance;
    }

    public byte[] getPackage() {
        return packet;
    }

    public Axis getAxis(int number) {
        return new Axis(number);
    }

    public Button getButton(int number) {
        return new Button(number);
    }

    private byte[] createPackage() {
        byte[] newPacket = new byte[Constants.REMOTE_CONTROL_PACKET_SIZE];
        newPacket[Constants.FRAME_TYPE_POSITION] = Constants.REMOTE_CONTROL_PACKET_ID;
        return newPacket;
    }

    class Button {

        private byte value;
        private final int number;
        private final int position;

        private Button(int number) {
            this.number = number;
            position = number + 33;
        }

        /**
         * @param value - значения 0 или 1
         */
        public void setValue(byte value) {
            this.value = value;
        }

        public void updatePackage() {
            packet[position] = value;
        }

        public void nullifyPackage() {
            packet[position] = 0;
        }
    }

    class Axis {

        private final int number;
        private final int position;
        private byte valueFirst, valueSecond;

        private Axis(int number) {
            this.number = number;
            position = (number + 1) * 2;
        }

        /**
         * @param speed - скорость (от -32768 до +32767)
         */
        public void setSpeed(short speed) {
            valueFirst = (byte) (speed & 0xFF);
            valueSecond = (byte) ((speed >> 8) & 0xFF);
        }

        public void updatePackage() {
            packet[position - 1] = valueFirst;
            packet[position] = valueSecond;
        }

        public void nullifyPackage() {
            packet[position - 1] = 0;
            packet[position] = 0;
        }
    }
}
