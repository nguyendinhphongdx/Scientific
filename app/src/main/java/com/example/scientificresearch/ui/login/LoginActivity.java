package com.example.scientificresearch.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.scientificresearch.Model.Login.ResponseModelLogin;
import com.example.scientificresearch.R;
import com.example.scientificresearch.Server.ApiService.StudentService;
import com.example.scientificresearch.Server.Socket.io.SocketConnect;
import com.example.scientificresearch.ui.main.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.scientificresearch.Model.Store.setCurrentUser;

public class LoginActivity extends AppCompatActivity {
    TextView edtMail, edtPass, edtUsername, tvSuggess;
    Button btnLogin,btnSignUp;
    RelativeLayout rlUsername;
    View viewLine;
    Boolean isLogin = true;
    ConstraintLayout skeleton;
    Boolean isFetch= false;
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
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        SocketConnect.getInstance().Connect();
//                        SocketConnect.mSocklet.connect();
                        Log.d("Login",response.body().getMessage());
                        setCurrentUser(response.body().getData());
                        ToastMessage(response.body().getMessage());
                    }else{
                        Log.d("Login",response.raw().toString());
                        ToastMessage(response.message());
                    }
                    skeleton.setVisibility(View.GONE);
                }
                @Override
                public void onFailure(Call<ResponseModelLogin> call, Throwable t) {
                    ToastMessage("Authenticated Failed !!");
                    Log.d("Login",t.toString());
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
    }
}
