package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class spendingHistory extends AppCompatActivity {

    private RecyclerView mExpenses_RecyclerView;
    private List<MyExpense> mExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending_history);

        mExpenses_RecyclerView = (RecyclerView) findViewById(R.id.listExpenses);
        mExpenses_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mExpenses = ExpenseBase.get().getExpenses();
        mExpenses_RecyclerView.setAdapter(new ExpenseAdapter(mExpenses));
    }

    class ExpenseAdapter extends RecyclerView.Adapter<ExpenseViewHolder> {
        private List<MyExpense> myExpenses;
        public ExpenseAdapter(List<MyExpense> expenses) {
            super();
        }

        @NonNull
        @Override
        public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ExpenseViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
            holder.bind(this.myExpenses.get(position));
        }

        @Override
        public int getItemCount() {
            return this.myExpenses.size();
        }
    }

    class ExpenseViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mAmount;
        private TextView mDate;
        private TextView mCategory;

        public ExpenseViewHolder(ViewGroup container) {
            super(LayoutInflater.from(spendingHistory.this).inflate(R.layout.expense_list_items, container, false));
            mTitle = (TextView) findViewById(R.id.titleTxt);
            mAmount = (TextView) findViewById(R.id.amountTxt2);
            mDate = (TextView) findViewById(R.id.dateTxt2);
            mCategory = (TextView) findViewById(R.id.catTxt2);
        }

        public void bind(MyExpense myexpense) {
            mTitle.setText(myexpense.getTitle());
            mAmount.setText(String.valueOf(myexpense.getAmount()));
            mDate.setText(myexpense.getDate());
            mCategory.setText(myexpense.getCategory());
        }
    }
}

