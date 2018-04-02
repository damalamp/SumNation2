package com.xindere.davidlamp.sumnation2;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.lang.Math;
import java.util.Random;
import android.widget.Chronometer;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {
    double goalTotal, currentTotal;
    Random rand = new Random();
    private int seconds=0;
    boolean running;
    TextView txtTimerView,goalView,currentTotalView;
    long startTime=0L,timeInMilliseconds=0L;
    Handler handler = new Handler();
    Runnable updateTimerThread = new Runnable(){
        @Override
        public void run(){
            timeInMilliseconds = SystemClock.uptimeMillis()-startTime;
            int seconds = (int)(timeInMilliseconds/1000);
            int milliseconds = (int)(timeInMilliseconds%1000);
            txtTimerView.setText(String.format("%2d",seconds)+"."+String.format("%03d",milliseconds));
            handler.postDelayed(this,0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goalTotal = 0;
        txtTimerView = findViewById(R.id.timerValue);
        currentTotalView = findViewById(R.id.current_total);
        goalView = findViewById(R.id.goal_total);
        }

    public void addValue(View v) {
        if (currentTotal < goalTotal) {
            int buttonNumber = Integer.parseInt(v.getTag().toString());
            currentTotal = currentTotal + Math.pow(2, buttonNumber);
            currentTotalView.setText(String.valueOf((int) (currentTotal)));
            if (currentTotal > goalTotal) {
                currentTotalView.setBackgroundColor(Color.RED);
                handler.removeCallbacks(updateTimerThread);
            }
            if (currentTotal == goalTotal) {
                currentTotalView.setBackgroundColor(Color.GREEN);
                running = false;
                handler.removeCallbacks(updateTimerThread);
            }
        }
    }

    public void resetGame(View v) {
        currentTotal = 0;
        currentTotalView.setBackgroundColor(Color.TRANSPARENT);
        currentTotalView.setText(String.valueOf((int) (currentTotal)));
        goalTotal = rand.nextInt(2047) + 1;
        goalView.setText(String.valueOf(goalTotal));
        startTime = SystemClock.uptimeMillis();
        handler.postDelayed(updateTimerThread,0);
    }
}
