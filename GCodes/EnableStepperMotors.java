package com.hendrik.CNCServer.GCodes;

/*
 * Created by hendrik on 02.05.15.
 */
public class EnableStepperMotors extends GCodeInstruction {
    public EnableStepperMotors(){
        super("M17", new char[]{});
    }
}
