package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Profile_v2 extends AppCompatActivity {

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
        addCat = (Button) findViewById((R.id.addCategory));
        addCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("add cat", "onClick: HAHAHAHAHAH");
            }
        });

        // Other buttons
        saveCh = (Button) findViewById(R.id.saveButton2);
        needHlp = (Button) findViewById(R.id.helpButton);

        saveCh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Profile_v2.this, "Modifications saved", Toast.LENGTH_SHORT).show();
            }
        });

        needHlp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("need help", "onClick: CHCHHCUCU");
            }
        });


    }
}