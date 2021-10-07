package com.example.scientificresearch.Ui.room;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scientificresearch.Adapter.RoomAdapter;
import com.example.scientificresearch.Model.Class.Class;
import com.example.scientificresearch.Model.Class.ResponseModelClass;
import com.example.scientificresearch.Model.Store;
import com.example.scientificresearch.R;
import com.example.scientificresearch.Server.ApiService.StudentService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RoomFragment extends Fragment {
    RecyclerView recyclerView;
    RoomAdapter adapter;
    List<Class> rooms =  Store.getClasses();
    ConstraintLayout progess_Class;
    public RoomFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.frag_room, container, false);
        progess_Class= view.findViewById(R.id.progess_Class);
        callApi(view);
        return view;
    }

    private void callApi(View view) {
        getAllClass(view);
    }

    private void getAllClass(View view) {
        progess_Class.setVisibility(View.VISIBLE);
        StudentService.studentService.getAllClass(Store.getCurentUser().getID()).enqueue(new Callback<ResponseModelClass>() {
            @Override
            public void onResponse(Call<ResponseModelClass> call, Response<ResponseModelClass> response) {
                if(response.isSuccessful()){
                    Store.setClasses(response.body().getData());
                    rooms = Store.getClasses();
                    setView(view);
                    setUp();
                    setListener();
                    Log.d("ROOM >>>", "onResponse:  "+response.body().getMessage());
                }else{
                    Log.d("ROOM >>>", "onResponse:  "+response.message());
                }
                progess_Class.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseModelClass> call, Throwable t) {
                Log.d("ROOM >>>", "onResponse:  "+t.getMessage());
                progess_Class.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
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