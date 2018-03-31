package com.xindere.davidlamp.sumnation2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.lang.Math;
import java.util.Random;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {
    int goalTotal;
    double currentTotal;
    Random rand = new Random();
    Chronometer mChronometer;
    //long startTime=0L, timeInMilliseconds=0L;
    //Handler timerHandler = new Handler();
    //TextView time_value;

    /*   Thread updateTimer = new Thread (new Runnable(){
           @Override
           public void run(){
               timeInMilliseconds = SystemClock.uptimeMillis()-startTime;
               int secs=(int) (timeInMilliseconds/1000);
               int millisecs=(int) (timeInMilliseconds%1000);
               time_value.setText(""+String.format("%3d",secs)+":"+millisecs);
               timerHandler.postDelayed(this,0);
           }
       });
       */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goalTotal = 0;
        TextView goalView = (TextView) findViewById(R.id.goal_total);
        //mChronometer = (Chronometer) findViewById(R.id.time_value);
        goalView.setText(String.valueOf(goalTotal));
    }

    public void addValue(View v) {
        int buttonNumber = Integer.parseInt(v.getTag().toString());
        currentTotal = currentTotal + Math.pow(2, buttonNumber);
        TextView currentTotalView = (TextView) findViewById(R.id.current_total);
        currentTotalView.setText(String.valueOf((int)(currentTotal)));
        if (currentTotal > goalTotal){
            currentTotalView.setBackgroundColor(Color.RED);
        }
        if (currentTotal == goalTotal){
            currentTotalView.setBackgroundColor(Color.GREEN);
        }

    }
    //kiki da bomb

    public void resetGame(View v){

        currentTotal = 0;
        TextView currentTotalView = (TextView) findViewById(R.id.current_total);
        currentTotalView.setBackgroundColor(Color.TRANSPARENT);
        currentTotalView.setText(String.valueOf((int)(currentTotal)));
        goalTotal = rand.nextInt(2047) + 1;
        TextView goalView = (TextView) findViewById(R.id.goal_total);
        goalView.setText(String.valueOf(goalTotal));
    }

}
