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

import com.example.scientificresearch.Common.Functions;
import com.example.scientificresearch.Model.Login.ResponseModelLogin;
import com.example.scientificresearch.Model.Test;
import com.example.scientificresearch.R;
import com.example.scientificresearch.Server.ApiService.StudentService;
import com.example.scientificresearch.ui.main.MainActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView edtMail, edtPass, edtUsername, tvSuggess;
    Button btnLogin,btnSignUp;
    RelativeLayout rlUsername;
    View viewLine;
    Boolean isLogin = true;
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
                ToastMessage("Login ..."+ edtMail.getText() + edtPass.getText());
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
        String mail = "phongnguyendx@gmail.com";
        String pass = "123456";
        Boolean info = true;
        ToastMessage(info.toString());
        if(info){
            StudentService.studentService.Login(mail,pass).enqueue(new Callback<ResponseModelLogin>() {
                @Override
                public void onResponse(Call<ResponseModelLogin> call, Response<ResponseModelLogin> response) {
                    if(response.isSuccessful()){

                        ResponseModelLogin object = response.body();
                        Functions.ShowToast(getApplicationContext(),"Login Success");
                        Log.d("Login",model);
                    }else{
                        Functions.ShowToast(getApplicationContext(),"Authentication Failed");
                    }
                }
                @Override
                public void onFailure(Call<ResponseModelLogin> call, Throwable t) {
                    Gson gson = new Gson();
                    Log.d("Login",gson.toJson(t));
                    Functions.ShowToast(getApplicationContext(),"Authentication Failed 2");
                }
            });
        } else {
            ToastMessage("Authenticated Failed !!");
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
    }
}
