package com.example.recomm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BookDetail extends AppCompatActivity {

    // 리뷰 탭뷰
    private RelativeLayout latestLayout;
    private RelativeLayout recommendLayout;
    private RelativeLayout myReviewLayout;
    private LinearLayout textBox; // 이거는 뭐가 잘못된 건지 보이면 안되는데 보여서 쓰는 코드니까 무시하면돼!!!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        latestLayout = findViewById(R.id.latestLayout);
        recommendLayout = findViewById(R.id.recommendLayout);
        myReviewLayout = findViewById(R.id.myReviewLayout);
        textBox = findViewById(R.id.textBox); // 무시히ㅏ면돼!!!


        // 작품 정보 탭뷰
        CustomTab customTab = new CustomTab();
        customTab.init();
    }

    // 최신순 텍스트 클릭 이벤트 처리
    public void showLatestLayout(View view) {
        latestLayout.setVisibility(View.VISIBLE);
        recommendLayout.setVisibility(View.GONE);
        myReviewLayout.setVisibility(View.GONE);
    }

    // 추천순 텍스트 클릭 이벤트 처리
    public void showRecommendLayout(View view) {
        latestLayout.setVisibility(View.GONE);
        recommendLayout.setVisibility(View.VISIBLE);
        myReviewLayout.setVisibility(View.GONE);
    }

    // 내리뷰 텍스트 클릭 이벤트 처리
    public void showMyReviewLayout(View view) {
        latestLayout.setVisibility(View.GONE);
        recommendLayout.setVisibility(View.GONE);
        myReviewLayout.setVisibility(View.VISIBLE);
    }

    class CustomTab implements View.OnClickListener {
        ColorStateList def;
        TextView item1;
        TextView item2;
        TextView select;
        RelativeLayout item1Layout;
        RelativeLayout item2Layout;

        public void init() {
            item1 = findViewById(R.id.item1);
            item1Layout = findViewById(R.id.item1Layout);
            item2Layout = findViewById(R.id.item2Layout);
            item2 = findViewById(R.id.item2);
            item1.setOnClickListener(this);
            item2.setOnClickListener(this);
            select = findViewById(R.id.select);
            def = item2.getTextColors();
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.item1) {
                select.animate().x(0).setDuration(100);
                item1.setTextColor(Color.WHITE);
                item2.setTextColor(def);
                item1Layout.setVisibility(View.VISIBLE);
                item2Layout.setVisibility(View.INVISIBLE);
                textBox.setVisibility(View.INVISIBLE);
                latestLayout.setVisibility(View.INVISIBLE);
            } else if (view.getId() == R.id.item2) {
                item1.setTextColor(def);
                item2.setTextColor(Color.WHITE);
                int size = item2.getWidth();
                select.animate().x(size).setDuration(100);
                item2Layout.setVisibility(View.VISIBLE);
                item1Layout.setVisibility(View.INVISIBLE);
                textBox.setVisibility(View.VISIBLE);
                latestLayout.setVisibility(View.VISIBLE);
            }
        }
    }
}