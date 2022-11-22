package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

        String[] frequencies =  {"Daily","Weekly","Monthly","Customize"};
        AutoCompleteTextView autoCompleteTxt;
        ArrayAdapter<String> adapterItems;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.profile);

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
        }
    }

