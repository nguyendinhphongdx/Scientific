package com.example.scientificresearch.Model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Store {
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<History> histories = new ArrayList<>();
    public static ArrayList<Course> Course = new ArrayList<>();
    public static ArrayList<Room> getRoom(){
        for(int i=0;i<50;i++){
            rooms.add(new Room("_id"+i,"Lớp "+i));
        }
        return rooms;
    }
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
}
