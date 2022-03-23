package com.example.seng405_assigment1_chessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{

    private Button countDownTimerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDownTimerButton = findViewById(R.id.countDownTimerButton);

        countDownTimerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openCountDownTimerActivity();
            }
        });
    }

    private void openCountDownTimerActivity()
    {
        Intent intent = new Intent(this, ChessClock.class);
        startActivity(intent);
    }
}