package com.example.scientificresearch.Notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

import com.example.scientificresearch.R;
import com.example.scientificresearch.Ui.main.MainActivity;

import static com.example.scientificresearch.SettingsApp.App.CHANNEL_ID;
import static com.example.scientificresearch.Ui.main.MainActivity.notificationManager;


public class SetUpNotify {

    public static void createLocalNotification(String title, String message, Context context) {
        Intent activityIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, activityIntent, 0);
        RemoteInput remoteInput = new RemoteInput.Builder("key_text_reply")
                .setLabel("Your answer...")
                .build();
        Intent replyIntent;
        PendingIntent replyPendingIntent = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            replyIntent = new Intent(context, DirectReplyReceiver.class);
            replyPendingIntent = PendingIntent.getBroadcast(context,
                    0, replyIntent, 0);
        } else {
            //start chat activity instead (PendingIntent.getActivity)
            //cancel notification with notificationManagerCompat.cancel(id)
        }
        NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(
                R.drawable.ic_send,
                "Reply",
                replyPendingIntent
        ).addRemoteInput(remoteInput).build();

        Intent broadcastIntent = new Intent(context, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(context,
                0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_appnotify)
                .setContentTitle(title)
                .setContentText(message)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.logo))
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message)
                        .setBigContentTitle(title)
                )
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .addAction(replyAction)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Open", actionIntent)
                .build();
        notificationManager.notify(1, notification);
    }
}
