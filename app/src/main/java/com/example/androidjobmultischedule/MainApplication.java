package com.example.androidjobmultischedule;

import android.app.Application;

import com.evernote.android.job.JobManager;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        JobManager.create(this)
                .addJobCreator(tag -> {
                    if(tag.equals(SampleJob.TAG)) {
                        return new SampleJob();
                    } else {
                        return null;
                    }
                });
    }
}
