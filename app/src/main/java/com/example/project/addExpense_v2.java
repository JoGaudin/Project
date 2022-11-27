package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class addExpense_v2 extends AppCompatActivity {

    // The expense itself
    public MyExpense newExpense;

    // Title expense
    public EditText expTitle;

    // Date
    public EditText dateTimeDisplay;
    public Calendar calendar;
    public SimpleDateFormat dateFormat;

    // Amount
    public EditText amount;
    public TextView symbol;
    public Button chCurr;

    // Categories list view
    public ListView listView;
    public Button addCat;
    public String[] categories_expense = {"Groceries", "Gas and Fuel", "Outgoing expenses", "Internet"};

    // Save expense
    public Button saveExp;
    public MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense_v2);

        // Title expense
        expTitle = (EditText) findViewById(R.id.expenseEdit);
        String expTitleVal = expTitle.getText().toString();

        // Date
        // Displays the current date by default
        dateTimeDisplay = (EditText)findViewById(R.id.dateEdit);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String dateHint = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(dateHint);
        // Getting the date input
        String dateTimeVal = dateTimeDisplay.getText().toString();

        // Add category
        listView = (ListView) findViewById(R.id.simplelistview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String> (this, R.layout.activity_simple_list_view, R.id.test, categories_expense);
        listView.setAdapter(arrayAdapter);

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