package com.example.scientificresearch.ui.room;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scientificresearch.Adapter.RoomAdapter;
import com.example.scientificresearch.Common.Functions;
import com.example.scientificresearch.Model.Room;
import com.example.scientificresearch.Model.Store;
import com.example.scientificresearch.R;

import java.util.ArrayList;


public class RoomFragment extends Fragment {
    RecyclerView recyclerView;
    RoomAdapter adapter;
    ArrayList<Room> rooms = Store.getRoom();
    public RoomFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.frag_room, container, false);
        setView(view);
        setUp();
        setListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Functions.ShowToast(getActivity(),"restart Room");
    }

    private void setUp() {
        adapter = new RoomAdapter(rooms,getActivity());
        recyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void setListener() {
    }

    private void setView(View view) {
        recyclerView = view.findViewById(R.id.recyclerRoom);
    }
}