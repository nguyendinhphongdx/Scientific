package com.example.scientificresearch.Model.Subject;

import java.util.List;

public class ResponseModelSubject {
    private String message;
    private Long totalResult;
    private List<Subject> data;

    public String getMessage() { return message; }
    public void setMessage(String value) { this.message = value; }

    public Long getTotalResult() { return totalResult; }
    public void setTotalResult(Long value) { this.totalResult = value; }

    public List<Subject> getData() { return data; }
    public void setData(List<Subject> value) { this.data = value; }
}
