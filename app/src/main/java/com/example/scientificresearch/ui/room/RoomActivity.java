package com.example.scientificresearch.ui.room;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import com.example.scientificresearch.Common.Functions;
import com.example.scientificresearch.Model.Room;
import com.example.scientificresearch.Model.Store;
import com.example.scientificresearch.R;
import com.example.scientificresearch.ui.main.MainActivity;
import com.example.scientificresearch.ui.slider.MemberFragment;
import com.example.scientificresearch.ui.slider.Slider_RoomFragment;
import com.github.florent37.hollyviewpager.HollyViewPager;

import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity {
    TextView tvTitle;
    ImageView moreHor,imgBack;
    ConstraintLayout fabAdd;
    HollyViewPager hollyViewPager;
    Room current_room;
    ArrayList<Room> rooms = Store.getRoom();
    DrawerLayout drawerTask;
    LinearLayout icSliderMember;
    FragmentContainerView frag_room_slider;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_task);
        setViews();
        setUp();
        setListeners();
    }

    private void setListeners() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        moreHor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_room_slider, Fragment.instantiate(getApplicationContext(),MemberFragment.class.getName()))
                        .commit();
                icSliderMember = findViewById(R.id.icSliderMember);
                if(icSliderMember==null){
                    Functions.ShowToast(getApplicationContext(),"members null");
                }else{
                    Functions.ShowToast(getApplicationContext(),"members not null");
                    icSliderMember.setOnClickListener(open_members);
                }
                drawerTask.openDrawer(GravityCompat.END);
            }
        });


    }

    private void setUp() {
        current_room = queryCurrentRoom();
        tvTitle.setText(current_room.getName());
    }

    private void setViews() {
        tvTitle = findViewById(R.id.tvTitle);
        moreHor = findViewById(R.id.moreHor);
        imgBack = findViewById(R.id.imgBack);
        hollyViewPager = findViewById(R.id.hollyViewPager);
        drawerTask = findViewById(R.id.drawerTask);
        frag_room_slider = findViewById(R.id.frag_room_slider);
    }
    private Room queryCurrentRoom() {
        String room_id =  getIntent().getStringExtra("room_id");
        for(int i=0;i<rooms.size();i++){
            if(rooms.get(i).get_id().equals(room_id)){
                return rooms.get(i);
            }
        }
        return null;
    }
    private View.OnClickListener open_members = new View.OnClickListener() {
        public void onClick(View v) {
            Functions.ShowToast(getApplicationContext(),"Open Settings");
        }
    };
}
