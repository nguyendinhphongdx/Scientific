package com.example.scientificresearch.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scientificresearch.Common.Functions;
import com.example.scientificresearch.Model.Room;
import com.example.scientificresearch.R;
import com.example.scientificresearch.ui.room.RoomActivity;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    private List<Room> ListRoom;
    private Context mContext;

    public RoomAdapter(List<Room> listRoom, Context mContext) {
        ListRoom = listRoom;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Nạp layout cho View biểu diễn phần tử room
        View roomView = inflater.inflate(R.layout.it_room, parent, false);
        ViewHolder viewHolder = new ViewHolder(roomView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room room = ListRoom.get(position);
        holder.tvRoom.setText(room.getName());
        holder.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,RoomActivity.class);
               i.putExtra("room_id",room.get_id());
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ListRoom.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private View itemview;
        public TextView tvRoom;
        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
            tvRoom = itemView.findViewById(R.id.tvRoom);
        }
    }
}
