package com.example.scientificresearch.Model;

import androidx.annotation.NonNull;

public class Student {
    String Name;
    String Classi;
    float Mark;

    public Student(String name, String aClass, float mark) {
        Name = name;
        Classi = aClass;
        Mark = mark;
    }

    public Student() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getClassi() {
        return Classi;
    }

    public void setClassi(String classi) {
        Classi = classi;
    }

    public float getMark() {
        return Mark;
    }

    public void setMark(float mark) {
        Mark = mark;
    }
}
