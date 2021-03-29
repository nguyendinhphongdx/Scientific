package com.example.scientificresearch.Model.Subject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.OffsetDateTime;

public class Subject {
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Long price;
    @SerializedName("totalSession")
    @Expose
    private Long totalSession;
    @SerializedName("createdAt")
    @Expose
    private OffsetDateTime createdAt;
    @SerializedName("updatedAt")
    @Expose
    private OffsetDateTime updatedAt;
    @SerializedName("v")
    @Expose
    private Long v;

    public Subject() {
    }

    public Subject(String description, String status, String type, String unit, String id, String name, Long price, Long totalSession, OffsetDateTime createdAt, OffsetDateTime updatedAt, Long v) {
        this.description = description;
        this.status = status;
        this.type = type;
        this.unit = unit;
        this.id = id;
        this.name = name;
        this.price = price;
        this.totalSession = totalSession;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.v = v;
    }

    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public String getUnit() { return unit; }
    public void setUnit(String value) { this.unit = value; }

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public Long getPrice() { return price; }
    public void setPrice(Long value) { this.price = value; }

    public Long getTotalSession() { return totalSession; }
    public void setTotalSession(Long value) { this.totalSession = value; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime value) { this.createdAt = value; }

    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime value) { this.updatedAt = value; }
}
