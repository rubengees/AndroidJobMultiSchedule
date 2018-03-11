package com.example.androidjobmultischedule;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

public class SampleJob extends Job {

    public static final String TAG = "sample";

    private static final String NOTIFICATION_CHANNEL_ID = "example";
    private static final String NOTIFICATION_CHANNEL_NAME = "Example";
    private static final int NOTIFICATION_ID = 4321;

    // Ugly way of counting the amount of job runs.
    private static int runCount = 0;

    public static void doScheduleAsync() {
        new JobRequest.Builder(TAG)
                .setExecutionWindow(1, 100)
                .setUpdateCurrent(true)
                .build()
                .scheduleAsync();
    }

    public static void doScheduleSync() {
        new JobRequest.Builder(TAG)
                .setExecutionWindow(1, 100)
                .setUpdateCurrent(true)
                .build()
                .schedule();
    }

    @NonNull
    @Override
    protected Result onRunJob(@NonNull Params params) {
        runCount++;

        createNotificationChannel();
        showNotification();

        return Result.SUCCESS;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getContext()
                    .getSystemService(Context.NOTIFICATION_SERVICE);

            if (notificationManager != null) {
                NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                        NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    private void showNotification() {
        Notification notification = new NotificationCompat.Builder(getContext(), NOTIFICATION_CHANNEL_ID)
                .setContentTitle("Android Job Multi Schedule")
                .setContentText("This is run " + runCount)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .build();

        NotificationManagerCompat.from(getContext()).notify(NOTIFICATION_ID, notification);
    }
}
