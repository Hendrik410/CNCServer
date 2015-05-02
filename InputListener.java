package com.hendrik.CNCServer;

import java.util.Scanner;

/*
 * Created by hendrik on 30.04.15.
 */
public class InputListener extends Thread {

    public CNCServer parent;

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        String cmd;
        while (true) {
            cmd = scanner.nextLine();
            switch(cmd.substring(0,4)){
                case "quit":
                    initiateClose();
                    break;

                case "send":
                    sendGCode(cmd);
                    break;

                case "help":
                    printHelp();
                    break;
            }
        }
    }

    public void initiateClose(){
        parent.disposeAll();
        System.exit(0);
    }
    public void sendGCode(String cmd){
        System.out.println("Sending: " + cmd.substring(5));
        parent.cncController.cncSerial.sendString(cmd.substring(5));
    }
    public void printHelp(){
        System.out.println("help:    show this message");
        System.out.println("quit:    quit the CNCServer instance");
    }
}
