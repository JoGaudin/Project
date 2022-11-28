package com.example.project;

import static java.lang.Double.parseDouble;
import static java.lang.Double.valueOf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
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
    public DecimalFormat round = new DecimalFormat("0.0");

    // Categories list view
    public ListView listView;
    public Button addCat;
    public String[] categories_expense = {"Groceries", "Gas and Fuel", "Outgoing expenses", "Internet"};
    String listItem = "";

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
        Log.v("cccccc",expTitleVal);

        // Date
        // Displays the current date by default
        dateTimeDisplay = (EditText)findViewById(R.id.dateEdit);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String dateHint = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(dateHint);
        // Getting the date input
        String dateTimeVal = dateTimeDisplay.getText().toString();
        Log.v("cccccc",dateTimeVal);

        // Amount
        chCurr = (Button) findViewById(R.id.changeCurr2);
        amount = (EditText) findViewById(R.id.amountEdit);
        symbol = (TextView) findViewById(R.id.currSymbol2);
        symbol.setText("£");

        chCurr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editTextVal = amount.getText().toString();
                if(editTextVal.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a budget value", Toast.LENGTH_LONG).show();
                }
                else {
                    switchCurrency(editTextVal);
                }
            }
        });
        double amountVal = Double.parseDouble(amount.getText().toString());
        String currencyVal = symbol.getText().toString();
        Log.v("cccccc",currencyVal);

        // Add category
        listView = (ListView) findViewById(R.id.simplelistview);
        updateCategories();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String> (this, R.layout.activity_simple_list_view, R.id.test, categories_expense);
        listView.setAdapter(arrayAdapter);

        // Happens when clicking on a category
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                listItem = (String) listView.getItemAtPosition(position);
            }
        });
        Log.v("cccccc",listItem);

        // Adding a category when clicking the "Add Category Button" by going to the addCategory activity
        addCat = (Button) findViewById((R.id.addCategory3));
        addCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Getting the categories array to the other activity
                Intent intent = new Intent(addExpense_v2.this, addCategory.class);
                startActivity(intent);
            }
        });

        // save expense leads to the sound effect
        newExpense = new MyExpense(expTitleVal, dateTimeVal, amountVal, currencyVal, listItem);
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);
        saveExp = (Button) findViewById(R.id.saveExpense);
        saveExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound();
                Log.e("aaaaaa",newExpense.getDate());
                Log.e("aaaaaa",newExpense.getTitle());
                Log.e("aaaaaa",newExpense.getCategory());
                Log.e("aaaaaa",String.valueOf(newExpense.getAmount()));
                Toast.makeText(getApplicationContext(), "Expense saved", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(addExpense_v2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void startSound() {
        if(mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public void updateCategories() {
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            categories_expense = extras.getStringArray("fromAddCat");
        }
    }

    public double convertToEuros(double poundVal){
        double resEuro;
        resEuro = 1.16*poundVal;
        return resEuro;
    }

    public double convertToPounds(double euroVal){
        double resPound;
        resPound = euroVal/1.16;
        return resPound;
    }

    public void switchCurrency(String editTextVal) {
        if(chCurr.getText() == "Switch to €") {
            // Changing the button value from € to £ and the symbol too
            symbol.setText("€");
            chCurr.setText("Switch to £");
            // Changing the value inside the editText
            double intEditText = parseDouble(editTextVal);
            double convertedVal = convertToEuros(intEditText);
            String resString = String.valueOf(round.format(convertedVal));
            amount.setText(resString);
        } else {
            symbol.setText("£");
            chCurr.setText("Switch to €");
            double intEditText = parseDouble(editTextVal);
            double convertedVal = convertToPounds(intEditText);
            String resString = String.valueOf(round.format(convertedVal));
            amount.setText(resString);
        }
    }

}