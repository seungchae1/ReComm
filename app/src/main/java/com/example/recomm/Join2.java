package com.example.recomm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Join2 extends AppCompatActivity {

    ImageButton imgbtn;
    EditText edit, edit2;
    boolean input, input2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join2);
        imgbtn = findViewById(R.id.imageButton);
        edit = findViewById(R.id.edit);
        input = input2 = false;

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
                }else{
                    input = true;
                }
            }
        });
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input && input2){
                    Intent intent = new Intent(getApplicationContext(), Join.class);
                    startActivity(intent);
                }
            }
        });

    }

}