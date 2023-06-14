package com.example.recomm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BookDetail extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        LinearLayout accLayout = findViewById(R.id.acc);
        TextView text1 = findViewById(R.id.text1);
        LinearLayout panel1 = findViewById(R.id.panel1);

        accLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text1.getVisibility() == View.GONE) {
                    text1.setVisibility(View.VISIBLE);
                    panel1.setVisibility(View.VISIBLE);
                } else {
                    text1.setVisibility(View.GONE);
                    panel1.setVisibility(View.GONE);
                }
            }
        });
    }
}