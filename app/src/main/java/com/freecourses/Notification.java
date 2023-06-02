package com.freecourses;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Notification extends FirebaseMessagingService {
//    @SuppressLint("newapi")
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        String title = message.getNotification().getTitle();
        String text = message.getNotification().getBody();
        String CHANNEL_ID = "Channe_1";
        CharSequence name;

        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel(CHANNEL_ID, "Mesaage Notification"
                    , NotificationManager.IMPORTANCE_HIGH);

            getSystemService(NotificationManager.class).createNotificationChannel(channel);
            Context context;
            android.app.Notification.Builder notify=new android.app.Notification.Builder(this,CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true);
            NotificationManagerCompat.from(this).notify(1,notify.build());
        }





        super.onMessageReceived(message);

    }
}
