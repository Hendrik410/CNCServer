package com.hendrik.CNCServer.GCodes;

/*
 * Created by hendrik on 02.05.15.
 */
public class SpindleOff extends GCodeInstruction {

    public SpindleOff(){
        super("M05", new char[]{});
    }
}
