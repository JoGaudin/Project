package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    // Frequencies Dropdown Menu
    String[] frequencies =  {"Daily","Weekly","Monthly","Customize"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    // Categories Listview
    String[] categories = {"Groceries", "Gas and Fuel", "Outgoing expenses", "Internet"};
    int[] categoryImages = {R.drawable.groceries, R.drawable.car, R.drawable.popcorn, R.drawable.internet};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        // Frequencies Dropdown Menu
        autoCompleteTxt = findViewById(R.id.auto_complete_txt);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,frequencies);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        // Categories Listview
        listView = (ListView) findViewById(R.id.listCategories);
        CustomBaseAdaptater customBaseAdaptater = new CustomBaseAdaptater(getApplicationContext(), categories, categoryImages);
        listView.setAdapter(customBaseAdaptater);

    }
}

