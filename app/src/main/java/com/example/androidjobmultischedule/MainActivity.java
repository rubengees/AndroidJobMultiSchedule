package com.example.androidjobmultischedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scheduleAsync(View view) {
        // Schedule twice to show the problem. Even though setUpdateCurrent is set,
        // the Job is run twice.

        SampleJob.doScheduleAsync();
        SampleJob.doScheduleAsync();
    }

    public void scheduleSync(View view) {
        // Schedule twice to show the problem. Here android-job behaves correctly.

        SampleJob.doScheduleSync();
        SampleJob.doScheduleSync();
    }
}
