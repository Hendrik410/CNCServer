package com.hendrik.CNCServer.GCodes;

/*
 * Created by hendrik on 02.05.15.
 */
public class ControlledArcClockwise extends GCodeInstruction {

    public ControlledArcClockwise(){
        super("G02", new char[]{'X', 'Y', 'I', 'J'});
    }
}
