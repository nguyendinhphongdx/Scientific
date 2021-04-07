package com.example.scientificresearch.Model.Login;

import com.example.scientificresearch.Model.Student.Student;

public class ResponseModelLogin {
    private String message;
    private Long totalResult;
    private Student data;

    public ResponseModelLogin(String message, Long totalResult, Student data) {
        this.message = message;
        this.totalResult = totalResult;
        this.data = data;
    }

    public String getMessage() { return message; }
    public void setMessage(String value) { this.message = value; }

    public Long getTotalResult() { return totalResult; }
    public void setTotalResult(Long value) { this.totalResult = value; }

    public Student getData() {
        return data;
    }

    public void setData(Student data) {
        this.data = data;
    }
}
