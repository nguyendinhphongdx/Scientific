package com.example.scientificresearch.Server.Socket.io;

import android.util.Log;

import com.example.scientificresearch.Model.Store;
import com.example.scientificresearch.Server.Config;

import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import static java.util.Collections.singletonMap;

public class SocketConnect {
    public static Socket mSocklet;
    private static SocketConnect INSTANCE = null;
    public static  SocketConnect getInstance() {
       if(INSTANCE == null){
           INSTANCE = new SocketConnect();
       }
       return  INSTANCE;
    }
    {
        IO.Options options = IO.Options.builder()
                .setAuth(singletonMap("userId", Store.getCurentUser().getID()))
                .build();
        mSocklet = IO.socket(URI.create(Config.url),options);
    }
    public  void Connect(){
        Log.d("Socket","is connecting ...");
        mSocklet.connect();
    }
}
