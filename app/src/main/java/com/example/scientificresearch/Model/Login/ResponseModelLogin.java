package com.example.scientificresearch.Model.Login;

public class ResponseModelLogin {
    private String message;
    private Long totalResult;
    private Login[] data;
    public String getMessage() { return message; }
    public void setMessage(String value) { this.message = value; }

    public Long getTotalResult() { return totalResult; }
    public void setTotalResult(Long value) { this.totalResult = value; }

    public Login[] getData() { return data; }
    public void setData(Login[] value) { this.data = value; }
}
