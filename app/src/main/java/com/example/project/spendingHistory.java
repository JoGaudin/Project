package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class spendingHistory extends AppCompatActivity {

    private ListView spendHist;
    private List<MyExpense> mExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending_history);

        // Displaying the expenses history with a listview
        spendHist = (ListView) findViewById(R.id.expenses_list);
        mExpenses = ExpenseBase.get().getExpenses();
        ExpenseListAdapter adapter = new ExpenseListAdapter(this, R.layout.expense_list_items, mExpenses);
        spendHist.setAdapter(adapter);
    }

}

