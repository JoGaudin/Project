package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

public class Profile_v2 extends AppCompatActivity {

    // Budget
    public Button chCurr;
    public EditText budgetInput;
    public DecimalFormat round = new DecimalFormat("0.0");
    public TextView symbol;
    private List<MyBudget> mBudgets;
    public MyBudget newBudget;

    // Categories list view
    public ListView listView;
    public Button addCat;
    public String[] categories_profile = {"Groceries", "Gas and Fuel", "Outgoing expenses", "Other"};

    // Frequencies Dropdown Menu
    public Spinner spinner;
    public String[] frequencies =  {"Daily","Weekly","Monthly","Customize"};
    public ArrayAdapter<String> adapterItems;

    // Save Button
    public Button saveCh;

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
        // Add category
        listView = (ListView) findViewById(R.id.simplelistview);
        updateCategories();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String> (this, R.layout.activity_simple_list_view, R.id.test, categories_profile);
        listView.setAdapter(arrayAdapter);

        // Adding a category when clicking the "Add Category Button" by going to the addCategory activity
        addCat = (Button) findViewById((R.id.addCategory2));
        addCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Getting the categories array to the other activity
                Intent intent = new Intent(Profile_v2.this, addCategory.class);
                intent.putExtra("from Profile", true);
                startActivity(intent);
            }
        });

        // Budget management
        chCurr = (Button) findViewById(R.id.changeCurr);
        budgetInput = (EditText) findViewById(R.id.budgetEdit);
        symbol = (TextView) findViewById(R.id.currSymbol);
        symbol.setText("??");
        budgetInput.setText(String.valueOf(getBudget()));

        // Change the currency
        chCurr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editTextVal = budgetInput.getText().toString();
                if(editTextVal.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a budget value", Toast.LENGTH_LONG).show();
                }
                else {
                    switchCurrency(editTextVal);
                }
            }
        });

        // Saving button
        saveCh = (Button) findViewById(R.id.saveButton2);
        mBudgets = BudgetBase.get().getBudgets();
        saveCh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double budgetVal = Double.parseDouble(budgetInput.getText().toString());
                newBudget = new MyBudget(budgetVal, String.valueOf(symbol.getText()));
                mBudgets.add(newBudget);
                Toast.makeText(Profile_v2.this, "Modifications saved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Profile_v2.this, MainActivity.class);
                startActivity(intent);
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

    public void switchCurrency(String editTextVal) {
        if(chCurr.getText() == "Switch to ???") {
            // Changing the button value from ??? to ?? and the symbol too
            symbol.setText("???");
            chCurr.setText("Switch to ??");
            // Changing the value inside the editText
            double intEditText = Double.parseDouble(editTextVal);
            double convertedVal = convertToEuros(intEditText);
            String resString = String.valueOf(round.format(convertedVal));
            budgetInput.setText(resString);
        } else {
            symbol.setText("??");
            chCurr.setText("Switch to ???");
            double intEditText = Double.parseDouble(editTextVal);
            double convertedVal = convertToPounds(intEditText);
            String resString = String.valueOf(round.format(convertedVal));
            budgetInput.setText(resString);
        }
    }

    public void updateCategories() {
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            categories_profile = extras.getStringArray("fromAddCat");
        }
    }

    // Getting the budget value to keep it updated if needed
    public double getBudget() {
        mBudgets = BudgetBase.get().getBudgets();
        if(mBudgets.size() > 0) {
            return mBudgets.get(mBudgets.size()-1).getAmount();
        }
        return 0.0;
    }
}