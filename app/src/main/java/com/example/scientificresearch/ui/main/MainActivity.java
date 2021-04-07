package com.example.scientificresearch.ui.main;

        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.EditText;
        import android.widget.ImageButton;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import androidx.constraintlayout.widget.ConstraintLayout;
        import androidx.core.view.GravityCompat;
        import androidx.drawerlayout.widget.DrawerLayout;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentContainerView;

        import com.example.scientificresearch.Common.Functions;
        import com.example.scientificresearch.R;
        import com.example.scientificresearch.ui.add.AddRoomActivity;
        import com.example.scientificresearch.ui.home.HomeFragment;
        import com.example.scientificresearch.ui.notification.NotificationFragment;
        import com.example.scientificresearch.ui.profile.ProfileFragment;
        import com.example.scientificresearch.ui.room.RoomFragment;
        import com.google.android.material.bottomnavigation.BottomNavigationView;
        import com.google.android.material.floatingactionbutton.FloatingActionButton;
        import com.google.android.material.navigation.NavigationView;

        import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageButton btnDrawer, btnSetting, btnSearch;
    NavigationView navDrawer;
    DrawerLayout drawer_layout;
    Toolbar toolbar;
    EditText edtSearch;
    ConstraintLayout cslFab;
    FloatingActionButton fabAdd, fabAddGroup, fabAddRoom;
    BottomNavigationView navBottom;
    Animation animation;
    FragmentContainerView frm_main;
    Boolean isClickedFab = false;
    ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        setViews();
        setUp();
        setListener();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    private void setUp() {
        fabAdd.setVisibility(View.GONE);
        setPositionCslFab();
        setUpFragment();
    }

    private void setListener() {
        btnDrawer.setOnClickListener(openDrawer);
        btnSetting.setOnClickListener(openSettings);
        btnSearch.setOnClickListener(actionSearch);
        fabAdd.setOnClickListener(openListFab);
        setListenerNavBottom();
        fabAddRoom.setOnClickListener(addRoom);
        fabAddRoom.setOnLongClickListener(addRoomLong);
    }



    private void setViews() {
        navDrawer = findViewById(R.id.navDrawer);
        drawer_layout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        btnDrawer = toolbar.findViewById(R.id.btnDrawer);
        btnSetting= toolbar.findViewById(R.id.btnSetting);
        btnSearch = toolbar.findViewById(R.id.btnSearch);
        edtSearch = toolbar.findViewById(R.id.edtSearch);
        cslFab = findViewById(R.id.cslFab);
        fabAdd = findViewById(R.id.fabAdd);
        fabAddGroup = findViewById(R.id.fabAddGroup);
        fabAddRoom = findViewById(R.id.fabAddRoom);
        frm_main = findViewById(R.id.frm_main);
        navBottom = findViewById(R.id.navBottom);
    }
    private void setListenerNavBottom() {
        navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()){
                    case R.id.navBottomHome:
                        setVisibleToolBar(true);
                        setVisibleFab(false);
                        switchFragment(0); break;
                    case R.id.navBottomRoom:
                        setVisibleToolBar(true);
                        setVisibleFab(true);
                        switchFragment(1); break;
                    case R.id.navBottomNotifi:
                        setVisibleToolBar(true);
                        setVisibleFab(false);
                        switchFragment(2); break;
                    case R.id.navBottomProfiles:
                        switchFragment(3);
                        setVisibleFab(false);
                        setVisibleToolBar(false); break;
                }
                return false;
            }
        });
    }

    private void setVisibleToolBar(boolean isEx) {
        toolbar.setVisibility(isEx==true?View.VISIBLE:View.GONE);
    }

    private void setVisibleFab(Boolean isEx) {
        fabAdd.setVisibility(isEx==true?View.VISIBLE:View.GONE);
    }

    private void switchFragment(int itemIndex) {
        Log.d("TAG", "switchFragment: "+fragments.get(itemIndex));
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frm_main, fragments.get(itemIndex),null)
                .commit();
    }

    private void setUpFragment() {
        fragments.add(Fragment.instantiate(getApplicationContext(),HomeFragment.class.getName()));
        fragments.add(Fragment.instantiate(getApplicationContext(),RoomFragment.class.getName()));
        fragments.add(Fragment.instantiate(getApplicationContext(),NotificationFragment.class.getName()));
//        fragments.add(Fragment.instantiate(getApplicationContext(), BlankFragment.class.getName()));
        fragments.add(Fragment.instantiate(getApplicationContext(),ProfileFragment.class.getName()));
        //HomeFragment{f45e1c1} (961131ff-03d9-4e05-aac5-dc22272b6c2c)}
        switchFragment(0);
    }
    @Override
    public void setSupportActionBar(androidx.appcompat.widget.Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
    }
    private View.OnClickListener openDrawer = new View.OnClickListener() {
        public void onClick(View v) {
            // do something when the button is clicked
            drawer_layout.openDrawer(GravityCompat.START);

        }
    };
    private View.OnClickListener openSettings = new View.OnClickListener() {
        public void onClick(View v) {
            Functions.ShowToast(MainActivity.this,"Open Settings");
        }
    };
    private View.OnClickListener actionSearch = new View.OnClickListener() {
        public void onClick(View v) {
            Functions.ShowToast(MainActivity.this,"Searching ..."+ edtSearch.getText());
        }
    };
    private View.OnClickListener openListFab = new View.OnClickListener() {
        public void onClick(View v) {
            isClickedFab = isClickedFab==true?false:true;
            if(isClickedFab){
                animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right);
            }else{
                animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_left);
            }
            fabAdd.startAnimation(animation);
            setPositionCslFab();
        }
    };
    private View.OnClickListener addRoom = new View.OnClickListener() {
        public void onClick(View v) {
               Intent i = new Intent(MainActivity.this, AddRoomActivity.class);
               startActivity(i);
        }
    };
    private View.OnLongClickListener addRoomLong = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Functions.ShowToast(getApplicationContext(),"Thêm phòng mới");
            return false;
        }
    };
    private void setPositionCslFab() {
        if(!isClickedFab){
            fabAddGroup.setVisibility(View.GONE);
            fabAddRoom.setVisibility(View.GONE);
//            fabAddGroup.startAnimation(animation);
//            fabAddRoom.startAnimation(animation);
        }else{
            animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
            fabAddGroup.setVisibility(View.VISIBLE);
            fabAddRoom.setVisibility(View.VISIBLE);
            fabAddGroup.startAnimation(animation);
            fabAddRoom.startAnimation(animation);
        }
    }
}

