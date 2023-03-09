package com.example.recomm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class Join extends AppCompatActivity {

    ImageButton imgbtn;
    EditText edit;
    Spinner spin;
    boolean input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        imgbtn = findViewById(R.id.imageButton);
        edit = findViewById(R.id.edit);
        spin = (Spinner) findViewById(R.id.Spinn);
        input = false;


        User user= new User();
        user.email=edit.getText().toString().concat("g");
        String[] mail = getResources().getStringArray(R.array.spinner_array);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        spin.setAdapter(arrayAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        user.email=edit.getText().toString().concat("g");
                        break;
                    case 1:
                        user.email=edit.getText().toString().concat("n");
                        break;
                    case 2:
                        user.email=edit.getText().toString().concat("d");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() <= 0 ){
                    input = false;
                    imgbtn.setImageResource(R.drawable.nredbtn);
                }else{
                    input = true;
                    imgbtn.setImageResource(R.drawable.redbtn);
                }
            }
        });

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input){
                    Intent intent = new Intent(getApplicationContext(), Join2.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            }
        });
    }
}