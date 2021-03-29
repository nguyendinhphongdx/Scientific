package com.example.scientificresearch.Model.Final;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Final {
    @SerializedName("mark")
    @Expose
    private String mark;
    @SerializedName("date")
    @Expose
    private Long date;

    public Final(String mark, Long date) {
        this.mark = mark;
        this.date = date;
    }

    public Final() {
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
