package com.example.scientificresearch.Model;

public class History {
    String user_id;
    Long timestamp;
    String content;

    public History(String user_id, Long timestamp, String content) {
        this.user_id = user_id;
        this.timestamp = timestamp;
        this.content = content;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
