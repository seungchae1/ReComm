package com.example.recomm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Login2 extends AppCompatActivity {

    CheckBox check;
    EditText edit, edit2;
    ImageButton imgbtn;
    TextView newpass;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        check = findViewById(R.id.checkBox);
        edit = findViewById(R.id.edit);
        edit2 = findViewById(R.id.edit2);
        imgbtn= findViewById(R.id.imageButton);
        newpass = findViewById(R.id.Newpass);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check.isChecked())
                    check.setBackground(getDrawable(R.drawable.check_af));
                else check.setBackground(getDrawable(R.drawable.check_be));
            }
        });
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            }
        });

        newpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Newpass.class);
                startActivity(intent);
            }
        });
    }
}