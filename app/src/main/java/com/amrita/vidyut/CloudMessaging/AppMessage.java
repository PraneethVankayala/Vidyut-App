package com.amrita.vidyut.CloudMessaging;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.amrita.vidyut.BottomNavigation.Home;
import com.amrita.vidyut.NotificationData;
import com.amrita.vidyut.MyOrder;
import com.amrita.vidyut.ObjectSerializer;
import com.amrita.vidyut.R;
import com.amrita.vidyut.Registration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

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

        Log.i("123",remoteMessage.getMessageId());
        NotificationData data = new NotificationData();
        data.setText(remoteMessage.getNotification().getTitle());
        data.setDesc(remoteMessage.getNotification().getBody());
        data.setImage(remoteMessage.getData().get("Image"));
        data.setTimeago(remoteMessage.getSentTime());
        addTask(data);

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

                    if (r.getEid() != 0) {

                        FirebaseMessaging.getInstance().subscribeToTopic("Workshop"+String.valueOf(r.getEid()))
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

                        FirebaseMessaging.getInstance().subscribeToTopic("Addons"+r.getPid())
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
            case 3:
                ArrayList<Registration> registration = (ArrayList<Registration>) (List<?>) list;
                for (Registration r : registration) {

                    if (r.getEid() != 0) {

                        FirebaseMessaging.getInstance().subscribeToTopic("Contests"+String.valueOf(r.getEid()))
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



    public void addTask(NotificationData t) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        NotificationData realmmessage = realm.createObject(NotificationData.class);
        realmmessage.setDesc(t.getDesc());
        realmmessage.setImage(t.getImage());
        realmmessage.setText(t.getText());
        realmmessage.setTimeago(t.getTimeago());
        realm.commitTransaction();
    }

}
