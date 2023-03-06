package com.example.recomm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.Serializable;

public class Join extends AppCompatActivity {

    ImageButton imgbtn;
    EditText edit;
    boolean input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        imgbtn = findViewById(R.id.imageButton);
        edit = findViewById(R.id.edit);
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
                    User user= new User();
                    user.email = edit.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), Join2.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            }
        });
    }
}