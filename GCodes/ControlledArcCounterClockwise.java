package com.hendrik.CNCServer.GCodes;

/*
 * Created by hendrik on 02.05.15.
 */
public class ControlledArcCounterClockwise extends GCodeInstruction {

    public ControlledArcCounterClockwise(){
        super("G03", new char[]{'X', 'Y', 'I', 'J'});
    }
}
