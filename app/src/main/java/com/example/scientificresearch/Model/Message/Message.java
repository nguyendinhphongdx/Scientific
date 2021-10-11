package com.example.scientificresearch.Model.Message;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */
public class Message{
    public String userSendId;
    public String userReceiveId;
    public String message;
    public String type;
    public String displayName;
    public long time;
    public String _id;
    public JSONObject getObjectMessage(){
        JSONObject json = new JSONObject();
        try {
            json.put("type","text");
            json.put("displayName",this.displayName);
            json.put("file","");
            json.put("time",this.time);
            json.put("message",this.message);
            json.put("userSendId",this.userSendId);
            json.put("userReceiveId",this.userReceiveId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getUserSendId() {
        return userSendId;
    }

    public void setUserSendId(String userSendId) {
        this.userSendId = userSendId;
    }

    public String getUserReceiveId() {
        return userReceiveId;
    }

    public void setUserReceiveId(String userReceiveId) {
        this.userReceiveId = userReceiveId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}


