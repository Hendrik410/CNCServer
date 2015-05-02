package com.hendrik.CNCServer.GCodes;

/*
 * Created by hendrik on 02.05.15.
 */
public class RelativePositioning extends GCodeInstruction {
    public RelativePositioning(){
        super("G91", new char[]{});
    }
}
