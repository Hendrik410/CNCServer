package com.hendrik.CNCServer.GCodes;

/*
 * Created by hendrik on 02.05.15.
 */
public class SetUnitsToMillimeters extends GCodeInstruction{
    public SetUnitsToMillimeters(){
        super("G21", new char[]{});
    }
}
