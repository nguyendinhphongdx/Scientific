package com.example.scientificresearch.Ui.splash;

import android.content.Intent;
import android.os.Bundle;

import com.example.scientificresearch.Ui.login.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.os.Handler;
import android.os.Looper;

import com.example.scientificresearch.R;
import com.example.scientificresearch.Ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);

        goToMain();
    }



    private void goToMain() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this,LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);
                finish();
            }
        },2000);
    }
}