package com.example.scientificresearch.Model.Subject;

public class Subject {
    private String className;
    private String name;
    private String _id;
    private String mark;
    private Long price;

    public String getClassName() { return className; }
    public void setClassName(String value) { this.className = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getID() { return _id; }
    public void setID(String value) { this._id = value; }

    public String getMark() { return mark; }
    public void setMark(String value) { this.mark = value; }

    public Long getPrice() { return price; }
    public void setPrice(Long value) { this.price = value; }
}
