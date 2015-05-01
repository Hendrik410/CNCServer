package com.hendrik.CNCServer;

import gnu.io.SerialPort;

/*
 * Created by hendrik on 30.04.15.
 */
public class CNCSerialSettings {
    public static final int BAUD_RATE = 115200;
    public static final int DATA_BITS = SerialPort.DATABITS_8;
    public static final int STOP_BITS = SerialPort.STOPBITS_1;
    public static final int PARITY = SerialPort.PARITY_NONE;
}
