package com.example.scientificresearch.Adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scientificresearch.Common.Functions;
import com.example.scientificresearch.Model.Schedule.Schedule;
import com.example.scientificresearch.R;

import java.util.List;

public class NotiAdapter  extends RecyclerView.Adapter<NotiAdapter.ViewHolder> {
    private List<Schedule> ListHistory;
    private Context mContext;

    public NotiAdapter(List<Schedule> ListHistory, Context mContext) {
        this.ListHistory = ListHistory;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View historyView = inflater.inflate(R.layout.it_noti, parent, false);
        NotiAdapter.ViewHolder viewHolder = new NotiAdapter.ViewHolder(historyView);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Schedule history = ListHistory.get(position);
        holder.tvInfo.setText(history.getSubject()+" - "+history.getTitle());
//        ZonedDateTime d = ZonedDateTime.parse(history.getStartTime());
//        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        String formattedDate = d.format(myFormatObj);
        Log.d("===> TIME",String.valueOf(history.getStartTime()));
        holder.txtDate.setText(history.getDay());
        holder.tvTimeNoti.setText(history.getStartTime());
        if(history.getStatus()!=null){
            holder.txtStatus.setText(history.getStatus());
        }
    }

    @Override
    public int getItemCount() {
        return ListHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private View itemview;
        public TextView tvInfo; 
        public TextView tvTimeNoti;
        public TextView txtDate;
        public TextView txtStatus;
        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
            tvInfo = itemView.findViewById(R.id.tvInfo);
            tvTimeNoti = itemView.findViewById(R.id.tvTimeNoti);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Functions.ShowToast(v.getContext(),tvTimeNoti.getText()+"");
                }
            });
        }
    }
}
