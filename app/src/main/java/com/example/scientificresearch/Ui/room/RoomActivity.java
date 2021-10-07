package com.example.scientificresearch.Ui.room;

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
import com.example.scientificresearch.Model.Class.Class;
import com.example.scientificresearch.Model.Store;
import com.example.scientificresearch.R;
import com.example.scientificresearch.Ui.slider.MemberFragment;
import com.example.scientificresearch.Ui.slider.Slider_RoomFragment;
import com.github.florent37.hollyviewpager.HollyViewPager;

import java.util.List;

public class RoomActivity extends AppCompatActivity {
    TextView tvTitle;
    ImageView moreHor,imgBack;
    ConstraintLayout fabAdd;
    HollyViewPager hollyViewPager;
    Class current_room;
    List<Class> rooms = Store.getClasses();
    DrawerLayout drawerTask;
    LinearLayout icSliderMember;
    TextView txtMember;
    FragmentContainerView frag_room_slider;
    Boolean isMember = false;
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
               switchDrawer(isMember);
            }
        });
    }

    private void switchDrawer(Boolean isMember) {
        if(!isMember){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frag_room_slider, Fragment.instantiate(getApplicationContext(),Slider_RoomFragment.class.getName()))
                    .commit();
            drawerTask.openDrawer(GravityCompat.END);
            icSliderMember = frag_room_slider.findViewById(R.id.icSliderMember);

            if(icSliderMember==null){
                Functions.ShowToast(getApplicationContext(),"Open Null");
            }else{
                Functions.ShowToast(getApplicationContext(),"Open NOT");
                txtMember = findViewById(R.id.txtMember);
                txtMember.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Functions.ShowToast(getApplicationContext(),"Open Settings");
                    }
                });
                icSliderMember.setOnClickListener(open_members);
            }
        }else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frag_room_slider, Fragment.instantiate(getApplicationContext(),MemberFragment.class.getName()))
                    .commit();
            drawerTask.openDrawer(GravityCompat.END);
        }
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
    private Class queryCurrentRoom() {
        String room_id =  getIntent().getStringExtra("room_id");
        for(int i=0;i<rooms.size();i++){
            if(rooms.get(i).getID().equals(room_id)){
                return rooms.get(i);
            }
        }
        return null;
    }
    private View.OnClickListener open_members = new View.OnClickListener() {
        public void onClick(View v) {
            isMember = true;
            Functions.ShowToast(getApplicationContext(),"Open Settings");
        }
    };
}
