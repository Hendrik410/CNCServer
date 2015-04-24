package com.hendrik.CNCServer;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/*
 * Created by hendrik on 21.04.15.
 */
public class CNCHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange http) throws IOException {
        String requestedPath = checkIfAskedForIndex(http.getRequestURI().getPath());
        int httpCode = 200;

        File requestedFile = new File(CNCServerRessources.PATH_HTML + requestedPath);
        if(requestedFile.exists()){
            System.out.println("Requested: " + requestedFile.getAbsolutePath() + ". 200 OK");
        } else {
            System.out.println("Requested: " + requestedFile.getAbsolutePath() + ". 404 File not found");
            requestedFile = new File(CNCServerRessources.PATH_404);
            System.out.println("Sending: " + requestedFile.getAbsolutePath() + " instead");
            httpCode = 404;
        }

        sendFile(http, requestedFile, httpCode);

    }

    private void sendFile(HttpExchange http, File file, int httpCode) throws IOException{
        FileInputStream dataStream = new FileInputStream(file);
        int bytesToSend = dataStream.available();

        Headers h = http.getResponseHeaders();
        h.add("Content-Type", getContentType(file));

        System.out.println("Sending " + bytesToSend + " bytes");
        http.sendResponseHeaders(httpCode, bytesToSend);
        OutputStream os = http.getResponseBody();
        int data;
        while((data = dataStream.read()) != -1){
            os.write(data);
        }
        os.close();
    }

    private String checkIfAskedForIndex(String path){
        if(path.endsWith("/")){
            return path + "index.html";
        }
        return path;
    }

    private String getContentType(File file){
        String path = file.getAbsolutePath();
        String ending = path.substring(path.lastIndexOf("."));
        switch(ending){
            case ".html":
            case ".htm":
                return "text/html";

            case ".js":
                return "text/javascript";

            case ".png":
                return "image/png";

            case ".css":
                return "text/css";

            default:
                return "text/plain";
        }
    }
}
