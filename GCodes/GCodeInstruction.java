package com.hendrik.CNCServer.GCodes;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/*
 * Created by hendrik on 02.05.15.
 */
public abstract class GCodeInstruction {
    protected String command;
    private char[] requiredParams;
    private Map<Character, String> params;
    private String description;

    public GCodeInstruction(String command, char[] requiredParams){
        this.command = command;
        this.requiredParams = requiredParams;
        params = new HashMap<>();
    }
    public GCodeInstruction(String command, char[] requiredParams, String description){
        this(command, requiredParams);
        this.description = description;
    }

    public void addParam(char key, String value){
        params.put(new Character(key), value);
    }

    public boolean checkForRequiredParams(){
        for(char param: requiredParams){
            if(!params.containsKey(new Character(param))){
                return false;
            }
        }
        return true;
    }

    public String getGCode(){
        if(!checkForRequiredParams()) return "";
        String gcode = command + " ";
        for(Entry<Character, String> param: params.entrySet()){
            gcode += param.getKey().charValue() + " " + param.getValue() + " ";
        }
        return gcode;
    }
}
