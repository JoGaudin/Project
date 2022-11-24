package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private Button toProfileBtn;
    private ImageButton toProfileImgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toProfileImgBtn = (ImageButton) findViewById(R.id.goToProfileImageButton);
        toProfileBtn = (Button) findViewById(R.id.goToProfileButton);


        toProfileImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openProfileActivity();}
        });
        toProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfileActivity();
            }
        });
    }

    public void openProfileActivity(){
        Intent intent = new Intent(MainActivity.this, Profile_v2.class);
        intent.putExtra("activityOne", "I am from the First Activity!!");
        startActivity(intent);
    }
}