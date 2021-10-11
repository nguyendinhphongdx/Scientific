package com.example.scientificresearch.Model.Message;

import java.util.List;

public class MessageReceive {
    public List<String> users;
    public Message message;

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
