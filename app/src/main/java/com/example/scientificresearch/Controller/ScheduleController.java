package com.example.scientificresearch.Controller;

import android.content.Context;
import android.text.TextUtils;

import com.example.scientificresearch.Common.DatabaseHandler;
import com.example.scientificresearch.Model.Account;
import com.example.scientificresearch.Model.Memory;
import com.example.scientificresearch.Model.Schedule.Schedule;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScheduleController {
    private static String keyAlarm = "keyAlarm";
    public ScheduleController() {
    }

    public static void RememberAlarm(Schedule schedule, Context context){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseHandler handler = new DatabaseHandler(context);
                Memory memory = new Memory(keyAlarm,schedule.getId()+"");
                if(handler.existsMemory(keyAlarm)){
                    handler.updateMemory(memory);
                }else{
                    handler.addMemory(memory);
                }
            }
        }).start();
    }
    public static Memory GetRememberAlarm(Context context){
        final Memory[] memory = {new Memory()};
        DatabaseHandler handler = new DatabaseHandler(context);
        if(handler.existsMemory(keyAlarm)){
            memory[0] = handler.getMemory(keyAlarm);
        }
        return memory[0];
    }
    public static boolean ScheduleIsTurnOnAlarm(Context context,Long idSchedule){
        Memory memory = new Memory();
        DatabaseHandler handler = new DatabaseHandler(context);
        if(handler.existsMemory(keyAlarm)){
            memory = handler.getMemory(keyAlarm);
            String[] strings = memory.getValue().split(",");
            int isExist = -1;
            for (int i = 0; i < strings.length; i++) {
                if(strings[i].equals(idSchedule+"")){
                    isExist = i;
                }
            }
            if(isExist!=-1) return true;
            return  false;
        }
        return false;
    }
    public static List<Long> SchedulesIsTurningOn(Context context){
        Memory memory = new Memory();
        List<Long> list = new ArrayList<>();
        DatabaseHandler handler = new DatabaseHandler(context);
        if(handler.existsMemory(keyAlarm)){
            memory = handler.getMemory(keyAlarm);
            String[] strings = memory.getValue().split(",");
            for (int i = 0; i < strings.length; i++) {
                list.add(Long.parseLong(strings[i]));
            }
        }
        return list;
    }
    public static void SwitchAlarmSchedule(Context context,Long idSchedue){
        Memory memory = new Memory();
        DatabaseHandler handler = new DatabaseHandler(context);
        if(handler.existsMemory(keyAlarm)){
            memory = handler.getMemory(keyAlarm);
            String[] strings = memory.getValue().split(",");
            List<String> ids = new ArrayList<>();
            int isExist = -1;
            for (int i = 0; i < strings.length; i++) {
                if(strings[i].equals(idSchedue+"")){
                    isExist = i;
                }
                ids.add(strings[i]);
            }
            if(isExist==-1){
                ids.add(idSchedue+"");
            }else{
                ids.remove(isExist);
            }
            String string = "";
            for (int i = 0; i < ids.size(); i++) {
                if(i!=0){
                    string +=","+ids.get(i);
                }else{
                    string +=ids.get(i);
                }
            }
            Memory newMemory = new Memory(keyAlarm,string);
            handler.updateMemory(newMemory);
        }else{
            memory.setKey(keyAlarm);
            StringBuffer strings = new StringBuffer();
            strings.append(idSchedue);
            memory.setValue(strings.toString());
            handler.addMemory(memory);
        }

    }
}
