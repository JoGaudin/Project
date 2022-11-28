package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button toProfileBtn;
    private Button toAddExpense;
    private Button toDetailledView;
    private ImageButton toProfileImgBtn;
    private ProgressBar ProBarBudget;
    private TextView ProBarText;
    private TextView BudgetRemain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toProfileImgBtn = (ImageButton) findViewById(R.id.goToProfileImageButton);
        toProfileBtn = (Button) findViewById(R.id.goToProfileButton);
        toAddExpense = (Button) findViewById(R.id.buttonAddExpense);
        toDetailledView = (Button) findViewById(R.id.buttonVd);

        ProBarBudget = (ProgressBar) findViewById(R.id.progressBarBudget);
        ProBarText = (TextView) findViewById(R.id.textProgressBar);
        BudgetRemain = (TextView) findViewById(R.id.budgetremain);
        double var = 80;
        updateprogressbar(var);
        budgetremain(var);



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

    public void calculbudget(){

    }

    //Update the progressbar according to the percentage of budget spent
    public void updateprogressbar(double percent){
        ProBarBudget.setProgress((int) percent);
        ProBarText.setText(percent + "%");
    }

    //Display a text according to the percentage of budget spent
    public void budgetremain(double percent){
        int pourcentage = (int) percent;
        if((int) percent <= 50){
            BudgetRemain.setText("You have used less than 50% of your budget");
            BudgetRemain.setTextColor(Color.GREEN);
        }else if((int) percent > 50 && (int) percent < 75){
            BudgetRemain.setText("You have used more than 50% but less than 75% of your budget");
            BudgetRemain.setTextColor(Color.YELLOW);
            BudgetRemain.setTextSize(22);
        }else{
            BudgetRemain.setText("You have used more than 75% of your budget, be careful !");
            BudgetRemain.setTextColor(Color.RED);
        }
    }
}