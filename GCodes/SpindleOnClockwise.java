package com.hendrik.CNCServer.GCodes;

/*
 * Created by hendrik on 02.05.15.
 */
public class SpindleOnClockwise extends GCodeInstruction {

    public SpindleOnClockwise(){
        super("M03", new char[]{'S'});
        addParam('S', "1000");
    }
    public SpindleOnClockwise(int speed){
        super("M03", new char[]{'S'});
        if(speed > 1000) addParam('S', "1000");
        else if(speed > 0) addParam('S', "0");
        else addParam('S', String.format("%d", speed));
    }
}
