package com.example.scientificresearch.utils;

import android.util.JsonReader;

import com.example.scientificresearch.Model.Message.Message;
import com.example.scientificresearch.Model.Message.MessageReceive;
import com.example.scientificresearch.Model.Subject.Subject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class ConvertData {
    public static Float totalPrice(List<Subject> subjects){
        float total=0;
        for (int i = 0; i < subjects.size(); i++) {
            total += Float.parseFloat(subjects.get(i).getPrice()+"");
        }
        return total;
    }
    public static float avgMark(List<Subject> subjects){
        float total=0;
        for (int i = 0; i < subjects.size(); i++) {
            total += Float.parseFloat(subjects.get(i).getMark()+"");
        }
        return (float) Math.floor(total/subjects.size()*100)/100;
    }
    public static MessageReceive getMessageJSON(JSONObject result){
        MessageReceive messageReceive = new MessageReceive();
        Message message = new Message();
        List<String> users = new LinkedList<>();
        try {
            JSONObject _message = result.getJSONObject("message");
            JSONArray _users = result.getJSONArray("users");
            for(int i=0;i<_users.length();i++){
                users.add(_users.getString(i));
            }

            message._id =  _message.isNull("_id")?"1":_message.getString("_id");
            message.displayName = _message.getString("displayName");
            message.type = _message.getString("type");
            message.time = _message.getLong("time");
            message.userSendId = _message.getString("userSendId");
            message.userReceiveId = _message.getString("userReceiveId");
            message.message = _message.getString("message");
            messageReceive.users=users;
            messageReceive.message = message;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return messageReceive;
    }
}
