package com.example.scientificresearch.Model.Schedule;

import java.util.List;

public class ResponseModalSchedule {
    private String message;
    private Long totalResult;
    private List<Schedule> data;

    public String getMessage() { return message; }
    public void setMessage(String value) { this.message = value; }

    public Long getTotalResult() { return totalResult; }
    public void setTotalResult(Long value) { this.totalResult = value; }

    public List<Schedule> getData() { return data; }
    public void setData(List<Schedule> value) { this.data = value; }
}
