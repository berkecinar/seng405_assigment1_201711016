package com.example.seng405_assigment1_chessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;

public class CountDownTimerActivity extends AppCompatActivity {

    private boolean isPaused = false;
    private boolean isCanceled = false;

    private long timeRemaining = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_timer);

        final TextView tView = (TextView) findViewById(R.id.tv);
        final Button btnStart = (Button) findViewById(R.id.btn_start);
        final Button btnPause = (Button) findViewById(R.id.btn_pause);
        final Button btnResume = (Button) findViewById(R.id.btn_resume);
        final Button btnCancel = (Button) findViewById(R.id.btn_cancel);

        btnPause.setEnabled(false);
        btnResume.setEnabled(false);
        btnStart.setEnabled(false);

        btnStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                isPaused = false;
                isCanceled = false;

                btnStart.setEnabled(false);
                btnResume.setEnabled(false);

                btnPause.setEnabled(true);
                btnCancel.setEnabled(true);

                CountDownTimer timer;
                long millisInFuture = 30000; //30 seconds
                long countDownInterval = 1000; //1 seconds

                tView.setText(""+ millisInFuture / 1000);

                timer = new CountDownTimer(millisInFuture, countDownInterval)
                {
                    @Override
                    public void onTick(long millisUntilFinished)
                    {
                        if(isPaused || isCanceled)
                        {
                            cancel();
                        }
                        else
                        {
                            tView.setText(""+millisUntilFinished / 1000);

                            timeRemaining = millisUntilFinished;
                        }
                    }

                    @Override
                    public void onFinish()
                    {
                        tView.setText("Done");

                        btnStart.setEnabled(true);

                        btnResume.setEnabled(false);
                        btnPause.setEnabled(false);
                        btnCancel.setEnabled(false);
                    }
                }.start();
            }
        });

        btnPause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                isPaused = true;

                btnResume.setEnabled(true);
                btnCancel.setEnabled(true);

                btnStart.setEnabled(false);
                btnPause.setEnabled(false);
            }
        });

        btnResume.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                btnStart.setEnabled(false);
                btnResume.setEnabled(false);

                btnPause.setEnabled(true);
                btnCancel.setEnabled(true);

                isPaused = false;
                isCanceled = false;


                long millisInFuture = timeRemaining;
                long countDownInterval = 1000;

                new CountDownTimer(millisInFuture, countDownInterval){
                    public void onTick(long millisUntilFinished){
                        if(isPaused || isCanceled)
                        {
                            cancel();
                        }
                        else {
                            tView.setText("" + millisUntilFinished / 1000);

                            timeRemaining = millisUntilFinished;
                        }
                    }
                    public void onFinish(){
                        tView.setText("Done");

                        btnPause.setEnabled(false);
                        btnResume.setEnabled(false);
                        btnCancel.setEnabled(false);

                        btnStart.setEnabled(true);
                    }
                }.start();

                btnCancel.setOnClickListener(new OnClickListener(){
                    @Override
                    public void onClick(View v){
                        isCanceled = true;

                        btnPause.setEnabled(false);
                        btnResume.setEnabled(false);
                        btnCancel.setEnabled(false);

                        btnStart.setEnabled(true);

                        tView.setText("CountDownTimer Canceled/stopped.");
                    }
                });
            }
        });

        btnCancel.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                isCanceled = true;

                btnPause.setEnabled(false);
                btnResume.setEnabled(false);
                btnCancel.setEnabled(false);
                //Enable the start button
                btnStart.setEnabled(true);


                tView.setText("CountDownTimer Canceled/stopped.");
            }
        });
    }


}