package com.hendrik.CNCServer;

/*
 * Created by hendrik on 01.05.15.
 */
public class Message {
    public enum MessageType {GCODE, SETTINGS}

    public MessageType messageType;
    public boolean gotAnswer;
    public String answer;

    public Message(){
        messageType = MessageType.GCODE;
        gotAnswer = false;
        answer = "";
    }

    public Message(MessageType type){
        messageType = type;
        gotAnswer = false;
        answer = "";
    }

    public boolean setAnswer(String answer){
        this.answer = answer;
        this.gotAnswer = true;
        return answer.startsWith("ok");
    }
}
