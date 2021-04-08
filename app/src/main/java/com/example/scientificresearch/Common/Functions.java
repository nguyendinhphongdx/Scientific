package com.example.scientificresearch.Common;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.scientificresearch.Model.Schedule.Schedule;
import com.example.scientificresearch.Model.Store;

import java.util.List;

public class Functions {
    public static void ShowToast(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
    public static int pushDataChange(String _class,String start){
        int isExitst = -1;
        List<Schedule> schedules = Store.getSchedules();
        for (int i = 0; i < schedules.size(); i++) {
            Log.d("CONFIRM",schedules.get(i).getStartTime());
            Log.d("START",start);
            if(String.valueOf(schedules.get(i).getStartTime()).equals(start)){
                isExitst = i;
                break;
            }
        }
        return isExitst;
    }
    public static List<Schedule> changeDataSocket(int i,String des){
        List<Schedule> ls = Store.getSchedules();
        ls.get(i).setStatus(des);
        return ls;
    }
}
