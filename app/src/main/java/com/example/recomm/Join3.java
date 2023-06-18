package com.example.recomm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.recomm.Model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Join3 extends AppCompatActivity {

    ImageButton imgbtn;
    EditText edit, edit2;
    boolean input;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference db = mRootRef.child("user");

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
                Intent getintent = getIntent();
                User user = (User)getintent.getSerializableExtra("user");
                user.name = edit.getText().toString();
                user.setBirth(edit2.getText().toString());
                Intent intent = new Intent(getApplicationContext(), Login2.class);
                if(input) {
                    db.child(user.email).setValue(user);
                    startActivity(intent);
                }
            }
        });
    }
}