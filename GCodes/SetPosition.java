package com.hendrik.CNCServer.GCodes;

/*
 * Created by hendrik on 02.05.15.
 */
public class SetPosition extends GCodeInstruction {
    public SetPosition(){
        super("G92", new char[]{});
    }
}
