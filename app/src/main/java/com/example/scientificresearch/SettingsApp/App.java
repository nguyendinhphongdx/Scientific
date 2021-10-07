package com.example.scientificresearch.SettingsApp;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

public class App extends Application {
    public static final String CHANNEL_ID = "KhoaBangChanel";
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }
    private void createNotificationChannel() {
        Log.d("TAG", (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)+"");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Example Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("This is Channel ");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
