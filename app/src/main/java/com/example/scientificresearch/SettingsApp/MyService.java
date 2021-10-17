package com.example.scientificresearch.SettingsApp;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.scientificresearch.Notify.SetUpNotify;
import com.example.scientificresearch.R;
import com.example.scientificresearch.Server.Socket.io.SocketConnect;
import com.example.scientificresearch.Ui.main.MainActivity;
import com.example.scientificresearch.Ui.room.RoomActivity;

import static com.example.scientificresearch.SettingsApp.App.CHANNEL_ID;

public class MyService extends Service {
    private static final String TAG = MyService.class.getName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: start Service");
        SocketConnect.getInstance().Connect();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        SetUpNotify.createLocalNotification("Khoa Bang is running","App is listen socket",getApplicationContext());
        Intent i = new Intent(this, RoomActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("Title notification service example")
                .setContentText("app is run ning socket")
                .setContentIntent(pendingIntent)
                .build();
        startForeground(-1,notification);
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onCreate: cancel Service");
    }
}
