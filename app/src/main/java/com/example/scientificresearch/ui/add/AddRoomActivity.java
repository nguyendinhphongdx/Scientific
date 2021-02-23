package com.example.scientificresearch.ui.add;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.scientificresearch.Common.Functions;
import com.example.scientificresearch.Model.Room;
import com.example.scientificresearch.Model.Store;
import com.example.scientificresearch.Model.User;
import com.example.scientificresearch.R;
import com.example.scientificresearch.ui.main.MainActivity;
import com.example.scientificresearch.ui.room.RoomFragment;

import java.util.ArrayList;
import java.util.List;

public class AddRoomActivity extends AppCompatActivity {
    ArrayList<Room> rooms = Store.getRoom();
    ImageView imgCancel,imgAdd;
    EditText edtName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_room);
        setViews();
        setUp();
        setListeners();
    }

    private void setViews() {
        imgCancel    = findViewById(R.id.imgCancel);
        imgAdd       =findViewById(R.id.imgAdd);
        edtName      =findViewById(R.id.edtName);
    }

    private void setUp() {
    }

    private void setListeners() {
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtName.getText().length()==0){
                    Functions.ShowToast(getApplicationContext(),"Vui lòng không để trống tên phòng!");
                }else{
                    List<User> list = new ArrayList<>();
                    Log.d("TAG", "onClick: "+rooms.size()+"");
                    rooms.add(new Room("id_new", edtName.getText().toString(),list));
                    Functions.ShowToast(getApplicationContext(),"Thêm phòng thành công!");
                    finish();
                }
            }
        });
    }
}
