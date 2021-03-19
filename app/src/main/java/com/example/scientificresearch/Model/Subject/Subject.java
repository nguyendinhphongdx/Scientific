package com.example.scientificresearch.Model.Subject;

import java.time.OffsetDateTime;

public class Subject {
    private String description;
    private String status;
    private String type;
    private String unit;
    private String id;
    private String name;
    private Long price;
    private Long totalSession;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private Long v;

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
