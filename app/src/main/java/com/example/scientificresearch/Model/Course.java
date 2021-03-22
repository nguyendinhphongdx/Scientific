package com.example.scientificresearch.Model;

public class Course {
    String _id;
    String name;
    String className;
    float mark;

    public Course(String _id, String name, String className, float mark) {
        this._id = _id;
        this.name = name;
        this.className = className;
        this.mark = mark;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }
}
