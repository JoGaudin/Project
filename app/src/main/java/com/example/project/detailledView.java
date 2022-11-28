package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
    public TextView net;
    public TextView total;
    public DecimalFormat round = new DecimalFormat("0.0");

    // Percentages
    public TextView grosPct;
    public TextView gasPct;
    public TextView outgoingPct;
    public TextView netPct;

    // Progress Bars
    public ProgressBar grosPb;
    public ProgressBar gasPb;
    public ProgressBar outgoingPb;
    public ProgressBar netPb;
    int counter = 0;

    private List<MyExpense> myExpenseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailled_view);

        grocery = (TextView) findViewById(R.id.groceriesQty);
        gas = (TextView) findViewById(R.id.carQty);
        outgoing = (TextView) findViewById(R.id.popcornQty);
        net = (TextView) findViewById(R.id.internetQty);
        total = (TextView) findViewById(R.id.totalVal);

        myExpenseList = ExpenseBase.get().getExpenses();

        double total_value = getTotal(getAmount(grocery),getAmount(gas),getAmount(outgoing),getAmount(net));
        total.setText(String.valueOf(round.format(total_value)));

        // Setting the percentages
        grosPct = (TextView)findViewById(R.id.groceriesPct);
        gasPct = (TextView) findViewById(R.id.carPct);
        outgoingPct = (TextView) findViewById(R.id.popcornPct);
        netPct = (TextView) findViewById(R.id.internetPct);

        setPercentage(grosPct,grocery,total_value);
        setPercentage(gasPct,gas,total_value);
        setPercentage(outgoingPct,outgoing,total_value);
        setPercentage(netPct,net,total_value);

        // Progress bars
        grosPb = (ProgressBar)findViewById(R.id.groceryPb);
        gasPb = (ProgressBar)findViewById(R.id.carPb);
        outgoingPb = (ProgressBar)findViewById(R.id.popcornPb);
        netPb = (ProgressBar)findViewById(R.id.internetPb);

        int grosVal = ((int) getPercentage(grocery, total_value));
        int gasVal = ((int) getPercentage(gas, total_value));
        int outgoingVal = ((int) getPercentage(outgoing, total_value));
        int netVal = ((int) getPercentage(net, total_value));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            grosPb.setProgress(grosVal, true);
            gasPb.setProgress(gasVal, true);
            outgoingPb.setProgress(outgoingVal, true);
            netPb.setProgress(netVal, true);
        }
    }

    public double getTotal(double groc, double car, double popcorn, double internet) {
        return groc+car+popcorn+internet;
    }

    public double getAmount(TextView txtview) {
        String txt = txtview.getText().toString();
        return Double.parseDouble(txt);
    }

    public double getPercentage(TextView txtview, double total) {
        double amount = getAmount(txtview);
        double percentage = (amount/total)*100;
        return percentage;
    }

    public void setPercentage(TextView pct, TextView txtview, double total) {
        String percentage = String.valueOf(round.format(getPercentage(txtview,total)));
        percentage += "%";
        pct.setText(percentage);
    }

    /*
    public void setProgressBar(ProgressBar pb, int val) {
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run()
            {
                counter++;
                pb.setProgress(counter);
                if(counter == val)
                    t.cancel();
            }
        };
        t.schedule(tt,0,50);
    }

    /*
    public void setWidth(double total, TextView view) {


        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = newWidth;
        view.setLayoutParams(layoutParams);

    }
    */
}