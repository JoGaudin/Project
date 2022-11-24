package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class Categories extends AppCompatActivity {

    // Categories Listview
    String[] categories = {"Groceries", "Gas and Fuel", "Outgoing expenses", "Internet"};
    int[] categoryImages = {R.drawable.groceries, R.drawable.car, R.drawable.popcorn, R.drawable.internet};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // Categories Listview
        listView = (ListView) findViewById(R.id.list_categories);
        CustomBaseAdaptater customBaseAdaptater = new CustomBaseAdaptater(getApplicationContext(), categories, categoryImages);
        listView.setAdapter(customBaseAdaptater);
    }
}