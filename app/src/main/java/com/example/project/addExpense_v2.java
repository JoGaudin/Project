package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class addExpense_v2 extends AppCompatActivity {

    public Button saveExp;
    public MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense_v2);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);

        // save expense leads to the sound effect
        saveExp = (Button) findViewById(R.id.saveExpense);
        saveExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound();
            }
        });
    }

    public void startSound() {
        if(mediaPlayer != null) {
            mediaPlayer.start();
        }
    }
}