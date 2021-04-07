package com.example.scientificresearch.Server.Socket.io;

import android.util.Log;

import com.example.scientificresearch.Server.Config;

import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

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
    public  void Listenner(){
        mSocklet.on("receive_data",onNewNotification);
    }
    private Emitter.Listener onNewNotification = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject data = (JSONObject) args[0];
            String notif = data.optString("data");
            Log.d("SOCKET IO",notif);
        }
    };
}
