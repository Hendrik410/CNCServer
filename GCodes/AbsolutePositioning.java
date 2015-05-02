package com.hendrik.CNCServer.GCodes;

/*
 * Created by hendrik on 02.05.15.
 */
public class AbsolutePositioning extends GCodeInstruction {
    public AbsolutePositioning(){
        super("G90", new char[]{});
    }
}
