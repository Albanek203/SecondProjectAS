package com.example.secondprojectas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean isWork = false;
    private boolean wasWorking = false;
    private int second = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            second = savedInstanceState.getInt("second");
            isWork = savedInstanceState.getBoolean("isWork");
            wasWorking = savedInstanceState.getBoolean("wasWorking");
        }
        RunTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasWorking = isWork;
        isWork = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasWorking)
            isWork = true;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("second", second);
        savedInstanceState.putBoolean("isWork", isWork);
        savedInstanceState.putBoolean("wasWorking", wasWorking);
    }

    private void RunTimer() {
        final TextView timeView = findViewById(R.id.TxtTimer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second / 3600;
                int minutes = (second % 3600) / 60;
                int seconds = second % 60;
                String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                timeView.setText(time);
                if (isWork)
                    second++;
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void BtnStart_OnClick(View view) {
        isWork = true;
    }

    public void BtnStop_OnClick(View view) {
        isWork = false;
    }

    public void BtnReset_OnClick(View view) {
        isWork = false;
        second = 0;
    }

}