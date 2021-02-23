package com.example.scientificresearch.ui.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scientificresearch.Adapter.NotiAdapter;
import com.example.scientificresearch.Adapter.RoomAdapter;
import com.example.scientificresearch.Common.Functions;
import com.example.scientificresearch.Model.History;
import com.example.scientificresearch.Model.Room;
import com.example.scientificresearch.Model.Store;
import com.example.scientificresearch.R;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {
    RecyclerView recyclerView;
    NotiAdapter adapter;
    ArrayList<History> histories = Store.getHistories();
    public NotificationFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.frag_notification, container, false);
        setView(view);
        setUp();
        setListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Functions.ShowToast(getActivity(),"restart Noti");
    }

    private void setView(View view) {
        recyclerView = view.findViewById(R.id.recyclerNoti);
    }

    private void setUp() {
        adapter = new NotiAdapter(histories,getActivity());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setListener() {
    }
}
