package com.example.scientificresearch.Model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Store {
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<History> histories = new ArrayList<>();
    public static ArrayList<Room> getRoom(){
//        List<User> list = new ArrayList<>();
//        for(int i=0;i<5;i++){
//            list.add(new User("id_"+i,"user"+i,"1234","gmail"+i+"@bkav.com","phone"+i,new User.imageClass("ảnh"+i,"url"+i)));
//        }
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
}
