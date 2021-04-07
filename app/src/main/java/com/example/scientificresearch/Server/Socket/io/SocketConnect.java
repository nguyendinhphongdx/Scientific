package com.example.scientificresearch.Server.Socket.io;

import com.example.scientificresearch.Server.Config;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketConnect {
    public static Socket mSocklet ;
    private static SocketConnect INSTANCE = null;
    public static  SocketConnect getInstance() {
       if(INSTANCE == null){
           INSTANCE = new SocketConnect();
       }
       return  INSTANCE;
    }
    {
        try {
            mSocklet = IO.socket(Config.url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public  void Connect(){
        mSocklet.connect();
    }
}
