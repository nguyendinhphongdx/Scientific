package com.example.scientificresearch.Adapter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scientificresearch.Common.Functions;
import com.example.scientificresearch.Controller.ScheduleController;
import com.example.scientificresearch.Model.Schedule.Schedule;
import com.example.scientificresearch.R;
import com.example.scientificresearch.Ui.main.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.ViewHolder> {
    private List<Schedule> ListHistory;
    private Context mContext;
    private List<Long> scheduleTurnOn;
    private static final int TURNON =1;
    private static final int TURNOFF =0;

    public NotiAdapter(List<Schedule> ListHistory, Context mContext) {
        this.ListHistory = ListHistory;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        scheduleTurnOn =  ScheduleController.SchedulesIsTurningOn(mContext);
        LayoutInflater inflater = LayoutInflater.from(context);
        View historyView = inflater.inflate(R.layout.it_noti, parent, false);
        NotiAdapter.ViewHolder viewHolder = new NotiAdapter.ViewHolder(historyView);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Schedule history = ListHistory.get(position);
        holder.tvInfo.setText(history.getSubject() + " - " + history.getTitle());
        holder.txtDate.setText(history.getDay());
        holder.tvTimeNoti.setText(history.getStartTime());
        holder.idSchedule.setText(history.getId()+"");
        if(scheduleTurnOn.contains(Long.parseLong(history.getId()+""))){
            holder.imageNoti.setImageResource(R.drawable.ic_notifications_white_24dp);
            holder.imageNoti.setTag(R.string.activity,TURNON);
        }
        if (history.getStatus() != null) {
            holder.txtStatus.setText(history.getStatus());
        }
    }

    @Override
    public int getItemCount() {
        return ListHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvInfo;
        public TextView tvTimeNoti;
        public TextView txtDate;
        public TextView txtStatus;
        public ImageView imageNoti;
        private View itemview;
        public TextView idSchedule;

        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
            tvInfo = itemView.findViewById(R.id.tvInfo);
            tvTimeNoti = itemView.findViewById(R.id.tvTimeNoti);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            imageNoti = itemView.findViewById(R.id.imgNoti);
            idSchedule = itemView.findViewById(R.id.idSchedule);
            imageNoti.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        imageNoti.setImageResource(R.drawable.ic_notifications_white_24dp);
                        ScheduleController.SwitchAlarmSchedule(mContext,Long.parseLong(idSchedule.getText()+""));
                        String[] dateTime = tvTimeNoti.getText().toString().split(",");
                        String[] time = dateTime[1].split(":");

                        ArrayList<Integer> alarmDays = new ArrayList<Integer>();
                        alarmDays.add(Integer.parseInt(txtDate.getText().toString().split(" ")[1].trim()));
                        Functions.setAlarmClock(
                                Integer.parseInt(time[0].trim()),
                                Integer.parseInt(time[1].trim()),
                                tvInfo.getText() + "",
                                alarmDays,
                                mContext
                        );
                        if(( (Integer) imageNoti.getTag(1) == TURNON)){
                            imageNoti.setImageResource(R.drawable.ic_notification);
                            imageNoti.setTag(R.string.activity,TURNOFF);
                        }else{
                            imageNoti.setImageResource(R.drawable.ic_notifications_white_24dp);
                            imageNoti.setTag(R.string.activity,TURNON);
                        }
                    } catch (Exception e) {
                        Functions.ShowToast(mContext, e.getMessage());
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Functions.ShowToast(v.getContext(), txtDate.getText().toString().split(" ")[1].trim());

//                       Functions.ShowToast(v.getContext(),tvInfo.getText()+" - "+tvTimeNoti.getText()+"");
                    } catch (Exception e) {
                        Functions.ShowToast(v.getContext(), e.getMessage() + "");
                    }
                }
            });

        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();

        }
    }
}
