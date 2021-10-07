package com.example.scientificresearch.Model.Message;

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
}


