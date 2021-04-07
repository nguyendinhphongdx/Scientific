package com.example.scientificresearch.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Schedule history = ListHistory.get(position);
        holder.tvInfo.setText(history.getSubject()+" - "+history.getTitle());
//        Date date = new Date(history.getStartTime());
//        String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date);
        holder.txtDate.setText(history.getDay());
        holder.tvTimeNoti.setText(history.getStartTime());
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
        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
            tvInfo = itemView.findViewById(R.id.tvInfo);
            tvTimeNoti = itemView.findViewById(R.id.tvTimeNoti);
            txtDate = itemView.findViewById(R.id.txtDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Functions.ShowToast(v.getContext(),tvInfo.getText()+"");
                }
            });
        }
    }
}
