package com.example.code;

import android.os.Handler;
import android.widget.TextView;

import java.util.Locale;

public class Timer {
    private int time = 0;
    private Handler handler = new Handler();
    private Runnable runnable;
    private TextView timerTextView;

    public Timer(TextView timerTextView) {
        this.timerTextView = timerTextView;
    }

    public void startTimer() {
        runnable = new Runnable() {
            @Override
            public void run() {
                time++;
                int hours = time / 3600;
                int minutes = (time % 3600) / 60;
                int seconds = time % 60;
                String timeFormat = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
                timerTextView.setText(timeFormat);
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    public void stopTimer() {
        handler.removeCallbacks(runnable);
    }

    public int getTime() {
        return time;
    }
}