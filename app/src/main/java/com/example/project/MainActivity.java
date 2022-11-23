package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button toProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toProfileBtn = (Button) findViewById(R.id.goToProfileButton);
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