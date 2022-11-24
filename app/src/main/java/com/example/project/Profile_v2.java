package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Profile_v2 extends AppCompatActivity {

    //
    String[] categories = {"Groceries", "Gas and Fuel", "Outgoing expenses", "Internet"};
    ListView listView;

    // Frequencies Dropdown Menu
    Spinner spinner;
    String[] frequencies =  {"Daily","Weekly","Monthly","Customize"};
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_v2);

        // Frequencies Dropdown Menu
        spinner = findViewById(R.id.spiinner);

        adapterItems = new ArrayAdapter<String>(Profile_v2.this,android.R.layout.simple_spinner_item,frequencies);
        adapterItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterItems);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                String value = parent.getItemAtPosition(i).toString();
                Toast.makeText(Profile_v2.this, value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Listview
        listView = (ListView) findViewById(R.id.simplelistview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String> (this, R.layout.activity_simple_list_view, R.id.test, categories);
        listView.setAdapter(arrayAdapter);

    }
}