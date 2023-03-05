package com.example.recomm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Join3 extends AppCompatActivity {

    ImageButton imgbtn;
    EditText edit, edit2;
    boolean input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join3);

        imgbtn = findViewById(R.id.imageButton);
        edit = findViewById(R.id.edit);
        edit2 = findViewById(R.id.edit2);
        input = false;

        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                input = true;
                imgbtn.setImageResource(R.drawable.redbtn);
            }
        });

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login2.class);
                if(input) startActivity(intent);
            }
        });
    }
}