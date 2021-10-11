package com.example.scientificresearch.Model.Message;

import com.example.scientificresearch.Model.Student.Student;

import java.util.List;

public class ResponseModelMessage {
    private String message;
    private Long totalResult;
    private List<Message> data;

    public ResponseModelMessage(String message, Long totalResult, List<Message> data) {
        this.message = message;
        this.totalResult = totalResult;
        this.data = data;
    }

    public String getMessage() { return message; }
    public void setMessage(String value) { this.message = value; }

    public Long getTotalResult() { return totalResult; }
    public void setTotalResult(Long value) { this.totalResult = value; }

    public List<Message> getData() {
        return data;
    }

    public void setData(List<Message> data) {
        this.data = data;
    }
}
