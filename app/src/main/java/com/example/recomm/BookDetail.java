package com.example.recomm;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
public class BookDetail extends AppCompatActivity {

    private Button infoButton;
    private LinearLayout accordionLayout;
    private Button accordionButton;
    private TextView bookInfoTextView;

    private boolean isAccordionExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoButton = findViewById(R.id.infoButton);
        accordionLayout = findViewById(R.id.accordionLayout);
        accordionButton = findViewById(R.id.accordionButton);
        bookInfoTextView = findViewById(R.id.bookInfoTextView);
    }

    public void toggleAccordion(View view) {
        if (view.getId() == R.id.infoButton) {
            if (isAccordionExpanded) {
                accordionLayout.setVisibility(View.GONE);
                isAccordionExpanded = false;
            } else {
                accordionLayout.setVisibility(View.VISIBLE);
                isAccordionExpanded = true;
            }
        } else if (view.getId() == R.id.accordionButton) {
            if (bookInfoTextView.getVisibility() == View.VISIBLE) {
                bookInfoTextView.setVisibility(View.GONE);
            } else {
                bookInfoTextView.setVisibility(View.VISIBLE);
            }
        }
    }
}