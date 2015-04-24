package com.hendrik.CNCServer;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/*
 * Created by hendrik on 21.04.15.
 */
public class CNCAjaxHandler implements HttpHandler {

    private CNCController cncController;

    public CNCAjaxHandler(CNCController _controller){
        super();
        cncController = _controller;
    }

    @Override
    public void handle(HttpExchange http) throws IOException {
        System.out.println(http.getRequestURI().getPath());
        Headers headers = http.getRequestHeaders();
        if(headers.containsKey("type")){
            switch(headers.getFirst("type")){
                case "0":
                    handleManualControl(http);
                    break;

            }
        }
    }

    private void handleManualControl(HttpExchange http){
        Headers headers = http.getRequestHeaders();
        if(headers.containsKey("direction")){
            CNCDirection direction = CNCDirection.values()[Integer.parseInt(headers.getFirst("direction"))];
            cncController.move(direction);
        }
        if(headers.containsKey("drillToggle") && headers.containsKey("drillValue")){
            if(headers.getFirst("drillToggle").equals("0")){
                cncController.setDrill((byte)0);
            } else {
                cncController.setDrill(Byte.parseByte(headers.getFirst("drillValue")));
            }
        }
        if(headers.containsKey("continuousMovement")){
            if(headers.getFirst("continuousMovement").equals("false")){
                cncController.setContinuousMovement(false);
            } else {
                cncController.setContinuousMovement(true);
            }
        }

    }
}
