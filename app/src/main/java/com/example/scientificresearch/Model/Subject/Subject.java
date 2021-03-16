package com.example.scientificresearch.Model.Subject;

public class Subject {
    private String name;
    private int price;
    private  int totalSession;
    private String description;
    private String status;
    private String type;
    private String unit;

    public Subject(String name, int price, int totalSession, String description, String status, String type, String unit) {
        this.name = name;
        this.price = price;
        this.totalSession = totalSession;
        this.description = description;
        this.status = status;
        this.type = type;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalSession() {
        return totalSession;
    }

    public void setTotalSession(int totalSession) {
        this.totalSession = totalSession;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
