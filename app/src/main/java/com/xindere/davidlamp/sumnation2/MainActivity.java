package com.xindere.davidlamp.sumnation2;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.lang.Math;
import java.util.Random;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {
    double goalTotal, currentTotal,remainder;
    int clicksRequired,clicks,seconds,milliseconds,previousSeconds,previousMilliseconds;
    int buttonCount = 12;
    int base = 2;
    Random rand = new Random();
    TextView txtTimerView,goalView,currentTotalView,optimalView,clicksView;
    long startTime=0L,timeInMilliseconds=0L;
    Handler handler = new Handler();
    Runnable updateTimerThread = new Runnable(){
        @Override
        public void run(){
            timeInMilliseconds = SystemClock.uptimeMillis()-startTime;
            seconds = (int)((timeInMilliseconds+previousMilliseconds)/1000);
            milliseconds = (int)((timeInMilliseconds+previousMilliseconds)%1000);
            int deciseconds = milliseconds/100;
            txtTimerView.setText(String.format("%02d",seconds)+"."+String.format("%01d",deciseconds));
            handler.postDelayed(this,10);
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
        optimalView = findViewById(R.id.optimal);
        clicksView = findViewById(R.id.clicks);
        }

    @Override
    protected void onPause(){
        super.onPause();
        previousSeconds = seconds;
        previousMilliseconds = milliseconds;
        handler.removeCallbacks(updateTimerThread);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (goalTotal != 0) {
            startTime = SystemClock.uptimeMillis();
            Log.i("string1", "onResume: ");
            handler.postDelayed(updateTimerThread, 10);
        }
    }


    public void addValue(View v) {
        if (currentTotal < goalTotal) {
            int buttonNumber = Integer.parseInt(v.getTag().toString());
            currentTotal = currentTotal + Math.pow(base, buttonNumber);
            currentTotalView.setText(String.valueOf((int) (currentTotal)));
            if (currentTotal > goalTotal) {
                currentTotalView.setBackgroundColor(Color.RED);
                handler.removeCallbacks(updateTimerThread);
            }
            if (currentTotal == goalTotal) {
                currentTotalView.setBackgroundColor(Color.GREEN);
                handler.removeCallbacks(updateTimerThread);
                txtTimerView.setText(String.format("%02d",seconds)+"."+String.format("%03d",milliseconds));

            }
            clicks++;
            clicksView.setText(String.valueOf(clicks));
        }
    }

    public void resetGame(View v) {
        currentTotal = 0;
        clicksRequired=0;
        clicks = 0;
        currentTotalView.setBackgroundColor(Color.TRANSPARENT);
        currentTotalView.setText(String.valueOf((int) (currentTotal)));
        goalTotal = rand.nextInt(4095) + 1;
        remainder = goalTotal;
        for(int i=buttonCount;i>=0;i--){
            if(remainder-Math.pow(base,i)>=0){
                System.out.println("But not this far");
                remainder=remainder-Math.pow(base,i);
                clicksRequired++;
                System.out.println("Clicks Required: " + clicksRequired);
            }
            System.out.println("Remainder: " + remainder);
        }
        optimalView.setText(String.valueOf(clicksRequired));
        goalView.setText(String.valueOf((int) goalTotal));
        startTime = SystemClock.uptimeMillis();
        previousMilliseconds = 0;
        previousMilliseconds = 0;
        handler.postDelayed(updateTimerThread,10);
    }
}
