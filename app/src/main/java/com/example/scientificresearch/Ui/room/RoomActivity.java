package com.example.scientificresearch.Ui.room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scientificresearch.Adapter.MessageListAdapter;
import com.example.scientificresearch.Common.Functions;
import com.example.scientificresearch.Model.Class.Class;
import com.example.scientificresearch.Model.Message.Message;
import com.example.scientificresearch.Model.Message.MessageReceive;
import com.example.scientificresearch.Model.Message.ResponseModelMessage;
import com.example.scientificresearch.Model.Store;
import com.example.scientificresearch.Notify.SetUpNotify;
import com.example.scientificresearch.R;
import com.example.scientificresearch.Server.ApiService.StudentService;
import com.example.scientificresearch.Server.Socket.io.SocketConnect;
import com.example.scientificresearch.Ui.main.MainActivity;
import com.example.scientificresearch.Ui.slider.MemberFragment;
import com.example.scientificresearch.Ui.slider.Slider_RoomFragment;
import com.example.scientificresearch.utils.ConvertData;
import com.github.florent37.hollyviewpager.HollyViewPager;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.socket.emitter.Emitter;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public static RecyclerView mMessageRecycler;
    public static MessageListAdapter mMessageAdapter;
    public static List<Message> messageList = new ArrayList<>();
    public static EditText inputMessage;
    ImageButton buttonSend;
    ConstraintLayout skeleton;
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
        inputMessage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) mMessageRecycler.scrollToPosition(messageList.size()-1);
            }
        });
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendMessage();
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
        Store.setClassSelected(current_room);
        skeleton.setVisibility(View.VISIBLE);
        getAllMessage();
        SocketConnect.mSocklet.on("receive-message",onReceiveMessage);
        skeleton.setVisibility(View.GONE);
    }
    Emitter.Listener onReceiveMessage = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject result = (JSONObject) args[0];
            MessageReceive messageReceive = ConvertData.getMessageJSON(result);
            if(messageReceive.message.userSendId == current_room.getProfessor().getID()){
                messageList.add(messageReceive.message);
                updateMessages();
            }
        }
    };
    private void getAllMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> users = new ArrayList<String>();
                users.add(Store.getCurentUser().getID());
                users.add(current_room.getProfessor().getID());
                StudentService.studentService.queryAllMessage(users).enqueue(new Callback<ResponseModelMessage>() {
                    @Override
                    public void onResponse(Call<ResponseModelMessage> call, Response<ResponseModelMessage> response) {
                        if(response.isSuccessful()){
                            messageList = response.body().getData();
                            updateMessages();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseModelMessage> call, Throwable t) {

                    }
                });
            }
        }).start();
    }

    private void updateMessages(){
        mMessageRecycler.post(new Runnable() {
            @Override
            public void run() {
                mMessageAdapter = new MessageListAdapter(mMessageRecycler.getContext(), messageList);
                mMessageRecycler.setLayoutManager(new LinearLayoutManager(mMessageRecycler.getContext()));
                mMessageRecycler.setAdapter(mMessageAdapter);
                mMessageRecycler.scrollToPosition(messageList.size()-1);
                inputMessage.setText("");
            }
        });
    }
    public static void addMessageFromMainASocket(Message message){
        String id =  Store.getClassSelected().getProfessor().getID();
        if(message.userSendId.equals(id)){
            messageList.add(message);
            mMessageRecycler.post(new Runnable() {
                @Override
                public void run() {
                    mMessageAdapter = new MessageListAdapter(mMessageRecycler.getContext(), messageList);
                    mMessageRecycler.setLayoutManager(new LinearLayoutManager(mMessageRecycler.getContext()));
                    mMessageRecycler.setAdapter(mMessageAdapter);
                    mMessageRecycler.scrollToPosition(messageList.size()-1);
                    inputMessage.setText("");
                }
            });
        }
    }
    private void setViews() {
        tvTitle = findViewById(R.id.tvTitle);
        moreHor = findViewById(R.id.moreHor);
        imgBack = findViewById(R.id.imgBack);
        mMessageRecycler = findViewById(R.id.recycler_gchat);
        drawerTask = findViewById(R.id.drawerTask);
        frag_room_slider = findViewById(R.id.frag_room_slider);
        inputMessage = findViewById(R.id.input_message);
        buttonSend = findViewById(R.id.btnSend);
        skeleton = findViewById(R.id.cslProgress);

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

    private void SendMessage(){
        List<String> users = new ArrayList<String>();
        users.add(Store.getCurentUser().getID());
        users.add(current_room.getProfessor().getID());
        Message message = createObjectMessage();
        MessageReceive body = new MessageReceive();
        body.users = users;
        body.message = message;
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody requestBody =  FormBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(body));
                StudentService.studentService.chatToClass2(requestBody).enqueue(new Callback<MessageReceive>() {
                    @Override
                    public void onResponse(Call<MessageReceive> call, Response<MessageReceive> response) {
                        if(response.isSuccessful()){
                            messageList.add(message);
                            updateMessages();
                        }
                    }
                    @Override
                    public void onFailure(Call<MessageReceive> call, Throwable t) {

                    }
                });
            }
        }).start();
        SocketConnect.getInstance().EmitMessageToServer(body);
        messageList.add(message);
        updateMessages();
    }

    private Message createObjectMessage(){
        Message message = new Message();
        message.displayName = Store.getCurentUser().getName();
        message.userSendId = Store.getCurentUser().getID();
        message.userReceiveId = current_room.getProfessor().getID();
        message.message =inputMessage.getText().toString();
        message.time = new Date().getTime();
        return message;
    }
}
