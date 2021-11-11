package com.example.scientificresearch.Common;

import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.util.Log;
import android.widget.Toast;

import com.example.scientificresearch.Model.Schedule.Schedule;
import com.example.scientificresearch.Model.Store;

import java.util.ArrayList;
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
    public static void setAlarmClock(int hour,int minute,String message,ArrayList<Integer> days,Context context){
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_HOUR,hour);
        intent.putExtra(AlarmClock.EXTRA_MINUTES,minute);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE,message);
        intent.putExtra(AlarmClock.EXTRA_DAYS,days);
        if(intent.resolveActivity(context.getPackageManager())!= null){
            context.startActivity(intent);
        }else{
            Functions.ShowToast(context,"There is no app that support this action");
        }
    }
    public static void setAlarmClock(int hour,int minute,String message,Context context){
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_HOUR,hour);
        intent.putExtra(AlarmClock.EXTRA_MINUTES,minute);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE,message);
        if(intent.resolveActivity(context.getPackageManager())!= null){
            context.startActivity(intent);
        }else{
            Functions.ShowToast(context,"There is no app that support this action");
        }
    }
}
