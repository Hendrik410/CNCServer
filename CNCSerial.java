package com.hendrik.CNCServer;

import com.hendrik.CNCServer.GCodes.GCodeInstruction;
import gnu.io.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/*
 * Created by hendrik on 30.04.15.
 */
public class CNCSerial implements SerialPortEventListener{
    private CommPortIdentifier portIdentifier;
    private SerialPort serialPort;

    private BufferedReader serialReader;
    private PrintStream serialWriter;

    private Message lastMessage;

    public CNCSerial() throws Exception{
        getCommPortFromSelector();
        try{
            serialPort = (SerialPort)portIdentifier.open("CNCServer", 10000);
            serialPort.setSerialPortParams(CNCSerialSettings.BAUD_RATE, CNCSerialSettings.DATA_BITS, CNCSerialSettings.STOP_BITS, CNCSerialSettings.PARITY);
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);

            serialReader = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            serialWriter = new PrintStream(serialPort.getOutputStream(), true);
        } catch(PortInUseException e){
            System.out.println("Port is already in use by " + e.currentOwner);
            System.exit(0);
        }

    }


    public void sendRawString(String data) throws IOException {
        serialWriter.write(data.getBytes());
    }

    public void sendString(String gcode){
        try {
            sendRawString(gcode + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        lastMessage = new Message();
    }

    public void sendGCode(GCodeInstruction gcode){
        sendString(gcode.getGCode());
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        switch(serialPortEvent.getEventType()){
            case SerialPortEvent.DATA_AVAILABLE:
                getAvailableData(serialPortEvent);
                break;

        }
    }

    private void getAvailableData(SerialPortEvent event){
        try {
            CharBuffer buffer = CharBuffer.allocate(50);
            String data;
            //wait to let data arrive and read it
            Thread.sleep(1000);
            serialReader.read(buffer);

            //return if empty
            data = new String(buffer.array());
            if(data.isEmpty())return;

            //if answer of an GCode instruction, check if "ok"
            if(lastMessage != null && !lastMessage.gotAnswer && lastMessage.setAnswer(data)){
                System.out.println("got ok");
                return;
            }

            //if start message from grbl,read version
            if(data.startsWith("\r\nGrbl ")) {
                String grblVersion = data.substring(7, data.indexOf("[") - 1);
                System.out.println("Got Grbl start sequence. Version " + grblVersion);
                return;
            }

            //if nothing from above, just print
            System.out.println(data);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public CommPortIdentifier getCommPortFromSelector() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Enumeration portIdentifiers = CommPortIdentifier.getPortIdentifiers();

        List<CommPortIdentifier> identifierList = new ArrayList<>();

        while(portIdentifiers.hasMoreElements()){
            CommPortIdentifier pid = (CommPortIdentifier)portIdentifiers.nextElement();
            int elementId = identifierList.size() + 1;
            if(pid.getPortType() == CommPortIdentifier.PORT_SERIAL){
                System.out.println("(" + elementId + "): " + pid.getName());
                identifierList.add(pid);
            }
        }
        if(identifierList.size() == 0){
            endProgramIfNoPortFound();
            return null;
        } else {
            if(identifierList.size() == 1){
                portIdentifier = identifierList.toArray(new CommPortIdentifier[identifierList.size()])[0];
                System.out.println("Selected " + portIdentifier.getName() + " automaticly");
                return portIdentifier;
            }

            System.out.print("Select: ");
            int selected = Integer.parseInt(br.readLine());

            if(selected > 0 && selected <= identifierList.size()){
                portIdentifier = identifierList.toArray(new CommPortIdentifier[identifierList.size()])[selected - 1];
                return portIdentifier;
            } else {
                endProgramIfNoPortFound();
                return null;
            }
        }
    }

    public CommPortIdentifier getPortIdentifier(){
        return portIdentifier;
    }

    public void close(){
        try{
            if(serialWriter != null) serialWriter.close();
            if(serialReader != null) serialReader.close();
            if(serialPort != null) serialPort.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void endProgramIfNoPortFound(){
        System.out.println("No Serial-Port found!");
        System.exit(0);
    }
}
