package com.example.seng405_assigment1_chessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class ChessClock extends AppCompatActivity {

    private static final long startTimeInMillis = 600000;

    private static long clockOneTimeLeftInMillis = startTimeInMillis;
    private static long clockTwoTimeLeftInMillis = startTimeInMillis;

    private TextView clockOneCountDown;
    private TextView clockTwoCountDown;

    private CountDownTimer clockOneCountDownTimer;
    private CountDownTimer clockTwoCountDownTimer;

    private Button startPauseButton;
    private Button resetButton;

    private boolean isClockOneRunning;
    private boolean isClockTwoRunning;

    private Button switchButton1;
    private Button switchButton2;

    private boolean isPlayerOnePlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_clock);

        clockOneCountDown = findViewById(R.id.Counter1);
        clockTwoCountDown = findViewById(R.id.Counter2);

        startPauseButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);

        switchButton1 = findViewById(R.id.switchButton1);
        switchButton2 = findViewById(R.id.switchButton2);

        switchButton1.setEnabled(false);
        switchButton2.setEnabled(false);

        isClockOneRunning = false;
        isClockTwoRunning = false;

        isPlayerOnePlaying = true;

        startPauseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if(isClockOneRunning || isClockTwoRunning)
                {
                    PauseTimer();
                }
                else
                {
                    StartTimer();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                ResetTimer();
            }
        });

        UpdateCountDownText(3);
    }

    private void StartTimer()
    {
        if(isPlayerOnePlaying)
        {
            switchButton1.setEnabled(true);
            switchButton2.setEnabled(false);

            clockOneCountDownTimer = new CountDownTimer(clockOneTimeLeftInMillis, 1000) {
                @Override
                public void onTick(long l)
                {
                    clockOneTimeLeftInMillis = l;
                    UpdateCountDownText(1);
                }

                @Override
                public void onFinish()
                {
                    isClockOneRunning = false;

                    clockOneCountDown.setText("Time's Out");

                    startPauseButton.setText("Start");
                    startPauseButton.setVisibility(View.INVISIBLE);

                    resetButton.setVisibility(View.VISIBLE);

                    switchButton1.setVisibility(View.INVISIBLE);
                    switchButton2.setVisibility(View.INVISIBLE);
                }
            }.start();
            isClockOneRunning = true;
            isClockTwoRunning = false;

            startPauseButton.setText("Pause");
            resetButton.setVisibility(View.INVISIBLE);
        }
        else
        {
            switchButton1.setEnabled(false);
            switchButton2.setEnabled(true);

            clockOneCountDownTimer = new CountDownTimer(clockTwoTimeLeftInMillis, 1000) {
                @Override
                public void onTick(long l)
                {
                    clockTwoTimeLeftInMillis = l;
                    UpdateCountDownText(2);
                }

                @Override
                public void onFinish()
                {
                    isClockTwoRunning = false;

                    clockTwoCountDown.setText("Time's Out");

                    startPauseButton.setText("Start");
                    startPauseButton.setVisibility(View.INVISIBLE);

                    resetButton.setVisibility(View.VISIBLE);

                    switchButton1.setVisibility(View.INVISIBLE);
                    switchButton2.setVisibility(View.INVISIBLE);
                }
            }.start();
            isClockOneRunning = true;
            isClockTwoRunning = false;

            startPauseButton.setText("Pause");
            resetButton.setVisibility(View.INVISIBLE);
        }
    }

    private void PauseTimer()
    {
        if(isClockOneRunning)
        {
            clockOneCountDownTimer.cancel();

            isClockOneRunning = false;

            startPauseButton.setText("Start");
            resetButton.setVisibility(View.VISIBLE);
        }
        else if(isClockTwoRunning)
        {
            clockTwoCountDownTimer.cancel();

            isClockTwoRunning = false;

            startPauseButton.setText("Start");
            resetButton.setVisibility(View.VISIBLE);
        }
    }

    private void ResetTimer()
    {
        clockOneTimeLeftInMillis = startTimeInMillis;
        clockTwoTimeLeftInMillis = startTimeInMillis;

        UpdateCountDownText(3);

        resetButton.setVisibility(View.INVISIBLE);
        startPauseButton.setVisibility(View.VISIBLE);
    }

    private void UpdateCountDownText(int playerIndex)
    {
        if(playerIndex == 1)//Only Update Clock 1
        {
            int minutes = (int) (clockOneTimeLeftInMillis / 1000) / 60;
            int seconds = (int) (clockOneTimeLeftInMillis / 1000) % 60;

            String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

            clockOneCountDown.setText(timeLeftFormatted);
        }
        else if(playerIndex == 2)//Only Update Clock 1
        {
            int minutes = (int) (clockTwoTimeLeftInMillis / 1000) / 60;
            int seconds = (int) (clockTwoTimeLeftInMillis / 1000) % 60;

            String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

            clockTwoCountDown.setText(timeLeftFormatted);
        }
        else if(playerIndex == 3)//Update Both Clocks
        {
            int clockOneMinutes = (int) (clockOneTimeLeftInMillis / 1000) / 60;
            int clockOneSeconds = (int) (clockOneTimeLeftInMillis / 1000) % 60;

            int clockTwoMinutes = (int) (clockTwoTimeLeftInMillis / 1000) / 60;
            int clockTwoSeconds = (int) (clockTwoTimeLeftInMillis / 1000) % 60;

            String clockOneTimeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", clockOneMinutes, clockOneSeconds);
            String clockTwoTimeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", clockTwoMinutes, clockTwoSeconds);

            clockOneCountDown.setText(clockOneTimeLeftFormatted);
            clockTwoCountDown.setText(clockTwoTimeLeftFormatted);
        }
    }
}