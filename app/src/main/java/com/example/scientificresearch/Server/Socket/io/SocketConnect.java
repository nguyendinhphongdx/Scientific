package com.example.scientificresearch.Server.Socket.io;

import android.util.Log;

import com.example.scientificresearch.Model.Message.Message;
import com.example.scientificresearch.Model.Message.MessageReceive;
import com.example.scientificresearch.Model.Store;
import com.example.scientificresearch.Server.Config;
import com.google.gson.JsonObject;

import org.json.JSONException;
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
    public void EmitMessageToServer(MessageReceive message){
        JSONObject object = new JSONObject();
        JSONObject messageIn = new JSONObject();
        try {
            object.put("users",message.users);
            messageIn.put("userSendId",message.message.userSendId);
            messageIn.put("userReceiveId",message.message.userReceiveId);
            messageIn.put("message",message.message.message);
            messageIn.put("type",message.message.type);
            messageIn.put("time",message.message.time);
            messageIn.put("displayName",message.message.displayName);
            object.put("message",messageIn);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SocketConnect.mSocklet.emit("send-message",object);
    }
}
