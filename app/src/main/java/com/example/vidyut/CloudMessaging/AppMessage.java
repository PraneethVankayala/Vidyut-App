package com.example.vidyut.CloudMessaging;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.vidyut.Home;
import com.example.vidyut.MyOrder;
import com.example.vidyut.R;
import com.example.vidyut.Registration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class AppMessage extends FirebaseMessagingService {

    public static final String bodyofmsg = "body_of_message";
    public static final String titleofmsg = "title_of_message";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Intent intent = new Intent(this, Home.class);
        intent.putExtra(bodyofmsg,remoteMessage.getNotification().getBody());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.app_name);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle(remoteMessage.getNotification().getTitle())
                        .setContentText(remoteMessage.getNotification().getBody())
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Vidyut",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

    }

    public static void subscribetotopic(ArrayList<?> list, int usage) {
        switch (usage) {
            case 1:
                ArrayList<Registration> registrations = (ArrayList<Registration>) (List<?>) list;
                for (Registration r : registrations) {

                    if (r.getTitle() != null) {

                        FirebaseMessaging.getInstance().subscribeToTopic(r.getTitle().trim())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (!task.isSuccessful()) {
                                            Log.d(TAG, "Finished");
                                        } else {
                                            Log.d(TAG, "Error Subscribing Topic");
                                        }
                                    }
                                });

                    }
                }
                break;
            case 2 :     ArrayList<MyOrder> order = (ArrayList<MyOrder>) (List<?>) list;
                            for (MyOrder r : order) {

                                    if (r.getPid() != null) {
                                        Log.d("5555",r.getPid());

                                        FirebaseMessaging.getInstance().subscribeToTopic(r.getPid().trim())
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (!task.isSuccessful()) {
                                                            Log.d(TAG, "Finished");
                                                        } else {
                                                            Log.d(TAG, "Error Subscribing Topic");
                                                        }
                                                    }
                                                });

                                    }
                                }
                break;
        }


    }

}
