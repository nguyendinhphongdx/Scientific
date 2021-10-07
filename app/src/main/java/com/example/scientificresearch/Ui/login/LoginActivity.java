package com.example.scientificresearch.Ui.login;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import com.example.scientificresearch.Common.DatabaseHandler;
import com.example.scientificresearch.Controller.AuthController;
import com.example.scientificresearch.Model.Account;
import com.example.scientificresearch.Model.Login.ResponseModelLogin;
import com.example.scientificresearch.Model.Memory;
import com.example.scientificresearch.Notify.DirectReplyReceiver;
import com.example.scientificresearch.Notify.NotificationReceiver;
import com.example.scientificresearch.Notify.SetUpNotify;
import com.example.scientificresearch.R;
import com.example.scientificresearch.Server.ApiService.StudentService;
import com.example.scientificresearch.Server.Socket.io.SocketConnect;
import com.example.scientificresearch.Ui.main.MainActivity;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.scientificresearch.Model.Store.setCurrentUser;
import static com.example.scientificresearch.SettingsApp.App.CHANNEL_ID;

public class LoginActivity extends AppCompatActivity {
    TextView edtMail, edtPass, edtUsername, tvSuggess;
    Button btnLogin,btnSignUp;
    RelativeLayout rlUsername;
    View viewLine;
    Boolean isLogin = true;
    ConstraintLayout skeleton;
    Boolean isFetch= false;
    CheckBox remember;
    Memory memory;
    Account account = new Account();
    AuthController authController = new AuthController();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        setViews();
        setUp();
        setListeners();
    }

    private void setListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastMessage("resgestry ..."+ edtMail.getText() + edtPass.getText());
                isLogin = isLogin==true?false:true;
                setUp();
            }
        });
    }

    private void setUp() {
        memory = authController.GetRememberAccount(LoginActivity.this);
        Log.d("MEMORIES", "setUp: "+memory.getKey());
        if(isLogin){
            rlUsername.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);
            btnLogin.setText(R.string.sign_in);
            btnSignUp.setText(R.string.register);
        } else {
            rlUsername.setVisibility(View.VISIBLE);
            viewLine.setVisibility(View.VISIBLE);
            btnLogin.setText(R.string.register);
            btnSignUp.setText(R.string.sign_in);
        }
    }
    private void login() {
        skeleton.setVisibility(View.VISIBLE);
        String mail = edtMail.getText().toString();
        String pass = edtPass.getText().toString();
        Log.d("Info",mail+pass);
        Boolean info = true;
        if(info){
            StudentService.studentService.Login(mail,pass).enqueue(new Callback<ResponseModelLogin>() {
                @Override
                public void onResponse(Call<ResponseModelLogin> call, Response<ResponseModelLogin> response) {
                    if(response.isSuccessful()){
                        if(remember.isChecked()){
                            Account account = new Account(mail,pass);
                            authController.RememberAccount(account,LoginActivity.this);
                        }
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        setCurrentUser(response.body().getData());
                        SocketConnect.getInstance().Connect();
                        ToastMessage(response.body().getMessage());
                    }else{
                        Log.d("Login is unsuccess",response.raw().toString());
                        Toast.makeText(LoginActivity.this,"Login is unsuccess",Toast.LENGTH_SHORT).show();
                    }
                    skeleton.setVisibility(View.GONE);
                }
                @Override
                public void onFailure(Call<ResponseModelLogin> call, Throwable t) {
                    ToastMessage("Authenticated Failed !!");
                    Log.d("Login Failed",t.toString());
                    skeleton.setVisibility(View.GONE);
                }
            });
        } else {
            ToastMessage("Info not Empty!!");
            skeleton.setVisibility(View.GONE);
        }

    }
    private void ToastMessage(String message) {
        Toast.makeText(LoginActivity.this,message ,Toast.LENGTH_SHORT).show();
    }
    private void setViews() {
        edtMail = findViewById(R.id.edtMail);
        edtPass = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        edtUsername = findViewById(R.id.edtUsername);
        btnSignUp = findViewById(R.id.btnSignUp);
        rlUsername = findViewById(R.id.rlUsername);
        viewLine = findViewById(R.id.viewLine);
        tvSuggess = findViewById(R.id.tvSuggess);
        skeleton = findViewById(R.id.cslProgress);
        remember = findViewById(R.id.cbRemember);
    }
}
