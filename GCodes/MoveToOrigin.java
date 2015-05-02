package com.hendrik.CNCServer.GCodes;

/*
 * Created by hendrik on 02.05.15.
 */
public class MoveToOrigin extends GCodeInstruction {
    public MoveToOrigin(){
        super("G28", new char[]{});
    }
    public MoveToOrigin(boolean homeX, boolean homeY, boolean homeZ){
        super("G28", new char[]{});
        if(homeX) addParam('X', "");
        if(homeY) addParam('Y', "");
        if(homeZ) addParam('Z', "");
    }
}
