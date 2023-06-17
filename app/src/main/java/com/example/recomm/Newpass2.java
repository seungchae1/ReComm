package com.example.recomm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Newpass2 extends AppCompatActivity {

    ImageButton imgbtn;
    ImageView imgv;
    TextView textv;
    EditText edit, edit2;
    boolean samepass, input, input2;
    String userM;
    TextInputLayout t;
    String mypass;

    DataSnapshot myuser;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference(); //fire base
    DatabaseReference db = mRootRef.child("user");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpass2);
        Intent getintent = getIntent();
        userM = getintent.getStringExtra("userM");
        imgbtn = findViewById(R.id.imageButton);
        edit = findViewById(R.id.edit);
        edit2 = findViewById(R.id.edit2);
        samepass = input = input2 = false;
        t= findViewById(R.id.text2);
        imgv = findViewById(R.id.imageView2);
        textv = findViewById(R.id.textView5);
        mypass="";

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
                if(input && input2) imgbtn.setImageResource(R.drawable.redbtn);
            }
        });

        edit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() <= 0 ){
                    input2 = false;
                }else{
                    input2 = true;
                }
                if(input && input2) imgbtn.setImageResource(R.drawable.redbtn);
            }
        });
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass1 = edit.getText().toString();
                String pass2 = edit2.getText().toString();
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot s : snapshot.getChildren()) {
                            if(s.getKey().equals(userM)){
                                samepass = pass1.equals(s.child("pass").getValue().toString()) && !pass1.equals("") && !pass2.equals("");
                                if(samepass){
                                    HashMap<String, String> user = new HashMap<>();
                                    user.put("email", userM);
                                    user.put("name", s.child("name").getValue().toString());
                                    user.put("birth",s.child("birth").getValue().toString());
                                    user.put("pass",s.child("pass").getValue().toString());
                                    db.child(userM).updateChildren((HashMap)user);
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                }
                                else{
                                    t.setError(" ");
                                    t.setErrorIconDrawable(null);
                                    textv.setText("비밀번호를 확인해주세요.");
                                    imgv.setImageResource(R.drawable.error);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

}