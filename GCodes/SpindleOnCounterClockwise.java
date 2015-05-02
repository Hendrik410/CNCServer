package com.hendrik.CNCServer.GCodes;

/*
 * Created by hendrik on 02.05.15.
 */
public class SpindleOnCounterClockwise extends GCodeInstruction {

    public SpindleOnCounterClockwise(){
        super("M04", new char[]{'S'});
        addParam('S', "1000");
    }
    public SpindleOnCounterClockwise(int speed){
        super("M04", new char[]{'S'});
        if(speed > 1000) addParam('S', "1000");
        else if(speed > 0) addParam('S', "0");
        else addParam('S', String.format("%d", speed));
    }
}
