package com.example.recomm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recomm.Model.User;
import com.google.android.material.textfield.TextInputLayout;

public class Join2 extends AppCompatActivity {

    ImageButton imgbtn;
    ImageView imgv;
    TextView textv;
    EditText edit, edit2;
    boolean samepass, input, input2;
    TextInputLayout t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join2);
        imgbtn = findViewById(R.id.imageButton);
        edit = findViewById(R.id.edit);
        edit2 = findViewById(R.id.edit2);
        samepass = input = input2 = false;
        t= findViewById(R.id.text2);
        imgv = findViewById(R.id.imageView2);
        textv = findViewById(R.id.textView5);

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
                Intent getintent = getIntent();
                User user = (User)getintent.getSerializableExtra("user");
                //user.setPass(edit.getText().toString());
                Intent intent = new Intent(getApplicationContext(), Join3.class);
                intent.putExtra("user", user);
                String pass1 = edit.getText().toString();
                String pass2 = edit2.getText().toString();
                samepass = pass1.equals(pass2) && !pass1.equals("") && !pass2.equals("");
                if(samepass){
                    startActivity(intent);
                }
                else{
                    t.setError(" ");
                    t.setErrorIconDrawable(null);
                    textv.setText("비밀번호를 확인해주세요.");
                    imgv.setImageResource(R.drawable.error);
                }
            }
        });
    }

}