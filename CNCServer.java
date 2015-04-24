package com.hendrik.CNCServer;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

/*
 * Created by hendrik on 21.04.15.
 */
public class CNCServer {
    public static CNCController cncController;

    public static void main(String[] args) throws Exception{
        System.out.println("CNCServer.main");

        cncController = new CNCController();

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/cncc", new CNCHttpHandler());
        server.createContext("/ajax", new CNCAjaxHandler(cncController));
        server.setExecutor(null);
        server.start();
        System.out.println("HttpServer started");
    }
}
