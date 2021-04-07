package com.example.scientificresearch.Model.Class;

import java.util.List;

public class ResponseModelClass {
    private String message;
    private Long totalResult;
    private List<Class> data;

    public String getMessage() { return message; }
    public void setMessage(String value) { this.message = value; }

    public Long getTotalResult() { return totalResult; }
    public void setTotalResult(Long value) { this.totalResult = value; }

    public List<Class> getData() { return data; }
    public void setData(List<Class> value) { this.data = value; }

}
