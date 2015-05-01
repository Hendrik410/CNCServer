package com.hendrik.CNCServer;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

/*
 * Created by hendrik on 21.04.15.
 */
public class CNCServer {
    public CNCController cncController;
    public InputListener inputListener;

    public CNCServer(){
        System.out.println("CNCServer.main");

        try{

            cncController = new CNCController();

            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/cncc", new CNCHttpHandler());
            server.createContext("/ajax", new CNCAjaxHandler(cncController));
            server.setExecutor(null);
            server.start();
            System.out.println("HttpServer started");

            inputListener = new InputListener();
            inputListener.parent = this;
            inputListener.start();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void disposeAll(){
        cncController.closeAll();
    }

    public static void main(String[] args){
        CNCServer server = new CNCServer();
    }
}
