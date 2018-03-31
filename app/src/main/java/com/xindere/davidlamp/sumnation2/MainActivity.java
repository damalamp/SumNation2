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
    Chronometer stopWatch;
    Handler handler;
    private int seconds=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stopWatch = findViewById(R.id.stopWatch);
        goalTotal = 0;
        TextView goalView = findViewById(R.id.goal_total);
        goalView.setText(String.valueOf(goalTotal));
    }

    public void addValue(View v) {
        int buttonNumber = Integer.parseInt(v.getTag().toString());
        currentTotal = currentTotal + Math.pow(2, buttonNumber);
        TextView currentTotalView = findViewById(R.id.current_total);
        currentTotalView.setText(String.valueOf((int) (currentTotal)));
        if (currentTotal > goalTotal) {
            currentTotalView.setBackgroundColor(Color.RED);
        }
        if (currentTotal == goalTotal) {
            currentTotalView.setBackgroundColor(Color.GREEN);
        }

    }

    public void resetGame(View v) {
        currentTotal = 0;
        TextView currentTotalView = findViewById(R.id.current_total);
        currentTotalView.setBackgroundColor(Color.TRANSPARENT);
        currentTotalView.setText(String.valueOf((int) (currentTotal)));
        goalTotal = rand.nextInt(2047) + 1;
        TextView goalView = findViewById(R.id.goal_total);
        goalView.setText(String.valueOf(goalTotal));
        //stopWatch.setBase(SystemClock.elapsedRealtime());
        //stopWatch.start();
        handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                int secs = seconds;
                //int milliseconds = ;
                String time = String.format("%02d.%03d", seconds, seconds);
                handler.postDelayed(this, 0);
            }

        });

    }
}
