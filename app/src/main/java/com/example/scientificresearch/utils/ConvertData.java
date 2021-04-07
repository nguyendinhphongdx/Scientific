package com.example.scientificresearch.utils;

import com.example.scientificresearch.Model.Subject.Subject;

import java.util.List;

public class ConvertData {
    public static Float totalPrice(List<Subject> subjects){
        float total=0;
        for (int i = 0; i < subjects.size(); i++) {
            total += Float.parseFloat(subjects.get(i).getPrice()+"");
        }
        return total;
    }
    public static float avgMark(List<Subject> subjects){
        float total=0;
        for (int i = 0; i < subjects.size(); i++) {
            total += Float.parseFloat(subjects.get(i).getMark()+"");
        }
        return (float) Math.floor(total/subjects.size()*100)/100;
    }
}
