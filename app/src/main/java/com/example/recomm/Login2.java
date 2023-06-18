package com.example.recomm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.recomm.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login2 extends AppCompatActivity {

    CheckBox check;
    EditText edit, edit2;
    ImageButton imgbtn;
    TextView newpass;
    Spinner spin;
    String myemail= "g";
    boolean id, pass;
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
        spin = (Spinner) findViewById(R.id.Spinn);
        id = pass = false;

        String[] mail = getResources().getStringArray(R.array.spinner_array);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        spin.setAdapter(arrayAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        myemail="g";
                        break;
                    case 1:
                        myemail="n";
                        break;
                    case 2:
                        myemail="d";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference(); //fire base
        DatabaseReference db = mRootRef.child("user");

        String userPass = edit2.getText().toString();

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
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot s : snapshot.getChildren()) {
                            if(s.getKey().equals(myemail+edit.getText().toString())){
                                id =true;
                                if(s.child("pass").getValue().equals(edit2.getText().toString())){
                                    pass=true;
                                    User user=new User();
                                    user.email=s.child("email").toString();
                                    user.setPass(s.child("pass").toString());
                                    user.name=s.child("name").toString();
                                    user.setBirth(s.child("birth").toString());
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.putExtra("user",user);
                                    startActivity(intent);
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

        newpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Newpass.class);
                startActivity(intent);
            }
        });
    }
}