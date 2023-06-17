package com.example.recomm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Search extends AppCompatActivity {
        private TextView toTextView;
        private TextView yesTextView;
        private boolean isToggleOn = true; // 초기 상태를 toggle_on으로 설정

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search);

            toTextView = findViewById(R.id.to);
            yesTextView = findViewById(R.id.yes);

            toTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isToggleOn = !isToggleOn; // 상태를 반전시킴
                    updateToggleState();
                }
            });

            yesTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isToggleOn = !isToggleOn; // 상태를 반전시킴
                    updateToggleState();
                }
            });
        }

        private void updateToggleState() {
            if (isToggleOn) {
                // 오늘 활성화
                toTextView.setBackgroundResource(R.drawable.toggle_on);
                toTextView.setTextColor(getResources().getColor(R.color.white));

                // 어제 비활
                yesTextView.setBackgroundResource(R.drawable.toggle_off);
                yesTextView.setTextColor(0xFFA4A4A4);
            } else {
                // 오늘 비활
                toTextView.setBackgroundResource(R.drawable.toggle_off);
                toTextView.setTextColor(0xFFA4A4A4); // 헥스 코드 A4A4A4

                // 어제 활성화
                yesTextView.setBackgroundResource(R.drawable.toggle_on);
                yesTextView.setTextColor(getResources().getColor(R.color.white));
            }
        }
    }