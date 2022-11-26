package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private Button toProfileBtn;
    private Button toAddExpense;
    private Button toDetailledView;
    private ImageButton toProfileImgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toProfileImgBtn = (ImageButton) findViewById(R.id.goToProfileImageButton);
        toProfileBtn = (Button) findViewById(R.id.goToProfileButton);
        toAddExpense = (Button) findViewById(R.id.buttonAddExpense);
        toDetailledView = (Button) findViewById(R.id.buttonVd);


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
        toAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddExpense();
            }
        });
        toDetailledView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailedView();
            }
        });
    }

    public void openProfileActivity(){
        Intent intent = new Intent(MainActivity.this, Profile_v2.class);
        startActivity(intent);
    }

    public void openAddExpense(){
        Intent intent = new Intent(MainActivity.this, addExpense_v2.class);
        startActivity(intent);
    }

    public void openDetailedView(){
        Intent intent = new Intent(MainActivity.this, detailledView.class);
        startActivity(intent);
    }
}