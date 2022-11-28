package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ExpenseListAdapter extends ArrayAdapter<MyExpense> {
    private static final String TAG = "ExpenseListAdapter";
    private Context mContext;
    int mResource;

    public ExpenseListAdapter(Context context, int resource, List<MyExpense> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // get the expense information
        String title = getItem(position).getTitle();
        String date = getItem(position).getDate();
        double amount = getItem(position).getAmount();
        String category = getItem(position).getCategory();

        MyExpense expense = new MyExpense(title, date, amount, "'€", category);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView titleTxt = (TextView) convertView.findViewById(R.id.titleTxt);
        TextView amountTxt = (TextView) convertView.findViewById(R.id.amountTxt2);
        TextView dateTxt = (TextView) convertView.findViewById(R.id.dateTxt2);
        TextView categoryTxt = (TextView) convertView.findViewById(R.id.catTxt2);

        titleTxt.setText(title);
        amountTxt.setText(String.valueOf(amount));
        dateTxt.setText(date);
        categoryTxt.setText(category);
        return convertView;
    }
}
