package com.hendrik.CNCServer.GCodes;

/*
 * Created by hendrik on 02.05.15.
 */
public class DisableStepperMotors extends GCodeInstruction {
    public DisableStepperMotors(){
        super("M18", new char[]{});
    }
}
