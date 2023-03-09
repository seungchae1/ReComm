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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Newpass extends AppCompatActivity {

    ImageButton imgbtn;
    EditText edit;
    Spinner spin;
    boolean input;
    String userM;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference db = mRootRef.child("user");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpass);
        imgbtn = findViewById(R.id.imageButton);
        edit = findViewById(R.id.edit);
        spin = (Spinner) findViewById(R.id.Spinn);
        input = false;

        String[] mail = getResources().getStringArray(R.array.spinner_array);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        spin.setAdapter(arrayAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        userM=edit.getText().toString().concat("g");
                        break;
                    case 1:
                        userM=edit.getText().toString().concat("n");
                        break;
                    case 2:
                        userM=edit.getText().toString().concat("d");
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
                    intent.putExtra("userM",userM);
                    startActivity(intent);
                }
            }
        });
    }
}