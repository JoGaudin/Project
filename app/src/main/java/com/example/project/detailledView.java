package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class detailledView extends AppCompatActivity {

    // Amounts
    public TextView grocery;
    public TextView gas;
    public TextView outgoing;
    public TextView other;
    public TextView total;
    public DecimalFormat round = new DecimalFormat("0.0");

    // Percentages
    public TextView grosPct;
    public TextView gasPct;
    public TextView outgoingPct;
    public TextView otherPct;

    // Progress Bars
    public ProgressBar grosPb;
    public ProgressBar gasPb;
    public ProgressBar outgoingPb;
    public ProgressBar otherPb;
    int counter = 0;

    // Expense by category
    private List<MyExpense> myExpenseList;
    double groceries_expense = 0.0;
    double gas_expense = 0.0;
    double outgoing_expense = 0.0;
    double other_expense = 0.0;

    //Return
    public Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailled_view);

        // Button to go back home
        returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(detailledView.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Updating the values according to the expenses
        grocery = (TextView) findViewById(R.id.groceriesQty);
        gas = (TextView) findViewById(R.id.carQty);
        outgoing = (TextView) findViewById(R.id.popcornQty);
        other = (TextView) findViewById(R.id.OtherQty);
        total = (TextView) findViewById(R.id.totalVal);

        // Calcul all the expense by category
        myExpenseList = ExpenseBase.get().getExpenses();
        for(int i = 0; i < myExpenseList.size(); i++){
            if(myExpenseList.get(i).getCategory() == "Groceries"){
                groceries_expense += myExpenseList.get(i).getAmount();
            }else if(myExpenseList.get(i).getCategory() == "Gas and Fuel"){
                gas_expense += myExpenseList.get(i).getAmount();
            }else if(myExpenseList.get(i).getCategory() == "Outgoing expenses"){
                outgoing_expense += myExpenseList.get(i).getAmount();
            }else{
                other_expense += myExpenseList.get(i).getAmount();
            }
        }

        //Set all the Text with the current expense of the category
        grocery.setText(String.valueOf(groceries_expense));
        gas.setText(String.valueOf(gas_expense));
        outgoing.setText(String.valueOf(outgoing_expense));
        other.setText(String.valueOf(other_expense));

        double total_value = getTotal(getAmount(grocery),getAmount(gas),getAmount(outgoing),getAmount(other));
        total.setText(String.valueOf(round.format(total_value)));

        // Setting the percentages
        grosPct = (TextView)findViewById(R.id.groceriesPct);
        gasPct = (TextView) findViewById(R.id.carPct);
        outgoingPct = (TextView) findViewById(R.id.popcornPct);
        otherPct = (TextView) findViewById(R.id.otherPct);

        setPercentage(grosPct,grocery,total_value);
        setPercentage(gasPct,gas,total_value);
        setPercentage(outgoingPct,outgoing,total_value);
        setPercentage(otherPct,other,total_value);

        // Progress bars updating
        grosPb = (ProgressBar)findViewById(R.id.groceryPb);
        gasPb = (ProgressBar)findViewById(R.id.carPb);
        outgoingPb = (ProgressBar)findViewById(R.id.popcornPb);
        otherPb = (ProgressBar)findViewById(R.id.otherPb);

        int grosVal = ((int) getPercentage(grocery, total_value));
        int gasVal = ((int) getPercentage(gas, total_value));
        int outgoingVal = ((int) getPercentage(outgoing, total_value));
        int otherVal = ((int) getPercentage(other, total_value));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            grosPb.setProgress(grosVal, true);
            gasPb.setProgress(gasVal, true);
            outgoingPb.setProgress(outgoingVal, true);
            otherPb.setProgress(otherVal, true);
        }
    }

    //Calculation of the total expense
    public double getTotal(double groc, double car, double popcorn, double other) {
        return groc+car+popcorn+other;
    }

    public double getAmount(TextView txtview) {
        String txt = txtview.getText().toString();
        return Double.parseDouble(txt);
    }

    //Calculation of the percent in relation of the total spent
    public double getPercentage(TextView txtview, double total) {
        double amount = getAmount(txtview);
        double percentage = (amount/total)*100;
        return percentage;
    }

    public void setPercentage(TextView pct, TextView txtview, double total) {
        String percentage = "";
        if(Double.isNaN(getPercentage(txtview,total))){
            percentage = "0.0";
        } else {
            percentage = String.valueOf(round.format(getPercentage(txtview,total)));
        }
        percentage += "%";
        pct.setText(percentage);
    }
}