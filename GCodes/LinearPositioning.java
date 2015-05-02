package com.hendrik.CNCServer.GCodes;

import java.util.Map;

/*
 * Created by hendrik on 02.05.15.
 */
public class LinearPositioning extends GCodeInstruction {

    public LinearPositioning(){
        super("G01", new char[]{});
    }

    public String getGCode(){
        if(params.size() > 19) return "";
        String gcode = command + " ";
        for(Map.Entry<Character, String> param: params.entrySet()){
            gcode += param.getKey().charValue() + " " + param.getValue() + " ";
        }
        return gcode;
    }
}
