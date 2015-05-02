package com.hendrik.CNCServer.GCodes;

/*
 * Created by hendrik on 02.05.15.
 */
public class SetUnitsToInches extends GCodeInstruction{
    public SetUnitsToInches(){
        super("G20", new char[]{});
    }
}
