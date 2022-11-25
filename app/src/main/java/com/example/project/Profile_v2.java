package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Profile_v2 extends AppCompatActivity {

    // Budget
    Button chCurr;
    EditText budgetInput;
    DecimalFormat round = new DecimalFormat("0.0");
    TextView symbol;

    // Categories list view
    String[] categories = {"Groceries", "Gas and Fuel", "Outgoing expenses", "Internet"};
    ListView listView;
    Button addCat;

    // Frequencies Dropdown Menu
    Spinner spinner;
    String[] frequencies =  {"Daily","Weekly","Monthly","Customize"};
    ArrayAdapter<String> adapterItems;

    // Other buttons
    Button saveCh;
    Button needHlp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_v2);

        // Frequencies Dropdown Menu
        spinner = findViewById(R.id.spiinner);

        adapterItems = new ArrayAdapter<String>(Profile_v2.this,android.R.layout.simple_spinner_item,frequencies);
        adapterItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterItems);

        // Listview
        listView = (ListView) findViewById(R.id.simplelistview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String> (this, R.layout.activity_simple_list_view, R.id.test, categories);
        listView.setAdapter(arrayAdapter);

        // Adding a category when clicking the "Add Category Button"
        addCat = (Button) findViewById((R.id.changeCurr));
        addCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("add cat", "onClick: HAHAHAHAHAH");
            }
        });

        // Other buttons
        saveCh = (Button) findViewById(R.id.saveButton2);

        saveCh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Profile_v2.this, "Modifications saved", Toast.LENGTH_SHORT).show();
            }
        });

        // Budget
        chCurr = (Button) findViewById(R.id.changeCurr);
        budgetInput = (EditText) findViewById(R.id.budgetEdit);
        symbol = (TextView) findViewById(R.id.currSymbol);

        chCurr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editTextVal = budgetInput.getText().toString();
                if(editTextVal.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a budget value", Toast.LENGTH_LONG).show();
                }
                else if(chCurr.getText() == "Switch to €") {
                    // Changing the value inside the editText
                    double intEditText = Double.parseDouble(editTextVal);
                    double convertedVal = convertToEuros(intEditText);
                    String resString = String.valueOf(round.format(convertedVal));
                    budgetInput.setText(resString);
                    // Changing the button value from € to £ and the symbol too
                    symbol.setText("€");
                    chCurr.setText("Switch to £");
                } else {
                    double intEditText = Double.parseDouble(editTextVal);
                    double convertedVal = convertToPounds(intEditText);
                    String resString = String.valueOf(round.format(convertedVal));
                    budgetInput.setText(resString);
                    symbol.setText("£");
                    chCurr.setText("Switch to €");
                }
                Log.i("change help", "onClick: yayaay");
            }
        });
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
}