package com.example.scientificresearch.Ui.notification;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scientificresearch.Adapter.NotiAdapter;
import com.example.scientificresearch.Common.Functions;
import com.example.scientificresearch.Model.Schedule.ResponseModalSchedule;
import com.example.scientificresearch.Model.Schedule.Schedule;
import com.example.scientificresearch.Model.Store;
import com.example.scientificresearch.Notify.SetUpNotify;
import com.example.scientificresearch.R;
import com.example.scientificresearch.Server.ApiService.StudentService;
import com.example.scientificresearch.Server.Config;
import com.example.scientificresearch.Server.Socket.io.SocketConnect;
import com.example.scientificresearch.Ui.main.MainActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    RecyclerView recyclerView;
    NotiAdapter adapter;
    EditText startDate,endDate;
    DatePickerDialog dpd;
    TimePickerDialog tpd;
    List<Schedule> schedules = new ArrayList<>();
    Boolean isStart= true;
    TextView txtArrow;
    ConstraintLayout progess_Notif;
    public static Socket mSocklet;
    {
        try {
            mSocklet = IO.socket(Config.url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public NotificationFragment() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.frag_notification, container, false);
        progess_Notif = view.findViewById(R.id.progess_Notif);
        callApi(view);
        return view;
    }

    private void callApi(View view) {
        progess_Notif.setVisibility(View.VISIBLE);
        StudentService.studentService.getAllSchedule(Store.getCurentUser().getID()).enqueue(new Callback<ResponseModalSchedule>() {
            @Override
            public void onResponse(Call<ResponseModalSchedule> call, Response<ResponseModalSchedule> response) {
                if(response.isSuccessful()){

                    Log.d("SCHEDULE",response.body().getMessage());
                    Store.setSchedules(response.body().getData());
                    schedules= Store.getSchedules();
                    setView(view);
                    setUp();
                    setListener();
                }else{
                    Log.d("SCHEDULE","Error");
                }
                progess_Notif.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseModalSchedule> call, Throwable t) {
                Log.d("SCHEDULE",t.getMessage());
                progess_Notif.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Functions.ShowToast(getActivity(),"restart Noti");
    }

    private void setView(View view) {
        recyclerView = view.findViewById(R.id.recyclerNoti);
        startDate = view.findViewById(R.id.start_date);
        endDate = view.findViewById(R.id.end_date);
        txtArrow = view.findViewById(R.id.txtArrow);
    }

    private void setUp() {
        adapter = new NotiAdapter(schedules,getActivity());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        SocketConnect.mSocklet.on("receive_data",onNewNotification);
        //mSocklet.on("receive_data",onNewNotification);
    }
    private void setListener() {
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart = true;
                openDialogDate();
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart = false;
                openDialogDate();
            }
        });
        txtArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SOCKET IO","emmitting...");
                SocketConnect.mSocklet.emit("from_mobile","hello server");
            }
        });
    }

    public void openDialogDate(){
        Calendar now = Calendar.getInstance();
        if(dpd == null) {
            dpd = DatePickerDialog.newInstance(
                    NotificationFragment.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
        } else {
            dpd.initialize(
                    NotificationFragment.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
        }
        dpd.setVersion(DatePickerDialog.Version.VERSION_2);
        dpd.show(requireFragmentManager(), "Datepickerdialog");
    }
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        Log.d("NOTIFICATION",date);
        if(isStart){
            startDate.setText(dayOfMonth+"/"+(++monthOfYear)+"/"+year);
        }else{
            endDate.setText(dayOfMonth+"/"+(++monthOfYear)+"/"+year);
        }
        dpd = null;
        Calendar now = Calendar.getInstance();
        if (tpd == null) {
            tpd = TimePickerDialog.newInstance(
                    NotificationFragment.this,
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    true
            );
        } else {
            tpd.initialize(
                    NotificationFragment.this,
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    now.get(Calendar.SECOND),
                    true
            );
        }
        tpd.show(requireFragmentManager(), "Timepickerdialog");
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        String secondString = second < 10 ? "0"+second : ""+second;
        String time = "You picked the following time: "+hourString+"h"+minuteString+"m"+secondString+"s";
        if(isStart){
            String date = startDate.getText().toString();
            startDate.setText(date+" - "+hourString+":"+minuteString+"'");
        }else{
            String date = endDate.getText().toString();
            endDate.setText(date+" - "+hourString+":"+minuteString+"'");
        }
        tpd = null;
    }
    private Emitter.Listener onNewNotification = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject result = (JSONObject) args[0];
                    Log.d("SOCKET IO",String.valueOf(result));
                    try {
                        Toast.makeText(getActivity(),result.getString("_class")+" thông báo : "+result.getString("des"),Toast.LENGTH_SHORT).show();
                        int index = Functions.pushDataChange(result.getString("_class"),result.getString("convertStart"));
                        Log.d("INDEX =====>",String.valueOf(index));
                        if(index!=-1){
                            Log.d("BEFORE CHANGE DATAA =====>",String.valueOf(schedules.get(index).getStatus()));
                            schedules = Functions.changeDataSocket(index,result.getString("des"));
                            Log.d("AFTER CHANGE DATAA =====>",String.valueOf(schedules.get(index).getStatus()));
                            updateRecycle(index,schedules);
                            SetUpNotify.createLocalNotification(result.getString("_class"),result.getString("des"), getActivity());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
    private void updateRecycle (int insertIndex,List<Schedule> ls){

        schedules = ls;
        adapter.notifyDataSetChanged();
    }
}

