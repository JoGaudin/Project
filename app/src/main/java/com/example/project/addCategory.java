package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class addCategory extends AppCompatActivity {

    public String[] categories_addCat = {"Groceries", "Gas and Fuel", "Outgoing expenses", "Internet"};
    public ListView listView;
    public Button submit;
    public EditText newCat;
    public ArrayList<String> categoriesAdded = new ArrayList<String>(Arrays.asList(categories_addCat));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        // Giving the categories information to the profile page
        Intent intent = new Intent(addCategory.this, Profile_v2.class);
        intent.putExtra("fromAddCat", categories_addCat);

        // Listview
        listView = (ListView) findViewById(R.id.simplelistview2);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String> (this, R.layout.activity_simple_list_view, R.id.test, categories_addCat);
        listView.setAdapter(arrayAdapter);

        // Submit button
        submit = (Button) findViewById(R.id.submitCat);
        newCat = (EditText) findViewById(R.id.addCatEdit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editTextVal = newCat.getText().toString();
                if(editTextVal.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a new category before submitting", Toast.LENGTH_LONG).show();
                }
                else {
                    if(categories_addCat.length < 7) {
                        categoriesAdded.add(editTextVal);
                        categories_addCat = categoriesAdded.toArray(categories_addCat);
                        Intent intent = new Intent(addCategory.this, Profile_v2.class);
                        intent.putExtra("fromAddCat", categories_addCat);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Too many categories, please delete one", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}