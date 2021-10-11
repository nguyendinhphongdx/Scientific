package com.example.scientificresearch.Model;

import android.util.Log;

import com.example.scientificresearch.Model.Class.Class;
import com.example.scientificresearch.Model.Schedule.Schedule;
import com.example.scientificresearch.Model.Student.Student;
import com.example.scientificresearch.Model.Subject.Subject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Store {
    public static Student currentUser;
    public static List<Class> classes= new ArrayList<>();
    public static ArrayList<History> histories = new ArrayList<>();
    public static ArrayList<Course> Course = new ArrayList<>();
    public static List<Subject> subjects = new ArrayList<>();
    public static List<Schedule> schedules = new ArrayList<>();
    public static Class classSelected = null;

    public static ArrayList<History> getHistories(){
//        User.imageClass image = new User.imageClass("name","url");
        Date date = new Date();
        for(int i=0;i<50;i++){
            histories.add(new History("_id"+i,date.getTime(),"lịch sử "+i+""));
        }
        return histories;
    }
    public static ArrayList<Course> getCourse(){
        for(int i=0;i<10;i++){
            Course.add(new Course(i+"","Name"+i+"","Class"+i+"", (float) 7.8));
        }
        return Course;
    }
    public static List<Subject> getSubject(){
        return subjects;
    }
    public static void setSubject(List<Subject> x){
        subjects = x;
    }
    public static void setCurrentUser(Student student){
         currentUser = student;
    }
    public static Student getCurentUser(){
        return currentUser;
    }
    public static void setClasses(List<Class> ds){
        classes = ds;
    }
    public static List<Class> getClasses(){
        return classes;
    }
    public static void setSchedules(List<Schedule> ds){
        schedules = ds;
    }
    public static List<Schedule> getSchedules(){
        return schedules;
    }
    public static List<String> getApplist(){
        List<String> rs = new ArrayList<>();
        rs.add("defaul");
        for (int i = 0; i < subjects.size(); i++) {
            Log.d("name",subjects.get(i).getClassName());
            rs.add(i+1,subjects.get(i).getClassName());
        }
        return rs;
    }

    public static Class getClassSelected() {
        return classSelected;
    }
    public static void setClassSelected(Class classSelected) {
        Store.classSelected = classSelected;
    }

    private class ClassSelected {

    }

}
