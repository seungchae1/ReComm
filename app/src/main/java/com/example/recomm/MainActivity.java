package com.example.recomm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 sliderViewPager;
    private LinearLayout layoutIndicator;

    private String[] images = new String[] {
            "R.drawable.img1",
            "R.drawable.img3",
            "R.drawable.img3"
    };


    private RecyclerView mRecyclerView;
    private ArrayList<RecyclerViewItem> mList;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private BookList Booklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //api
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://book.interpark.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getData("45B4DD127DD3DD5D8A37DCB8810027A5266CC3A45E174E40D8E0A6117008717E","101","json").enqueue(new Callback<BookList>() {
            @Override
            public void onResponse(Call<BookList> call, Response<BookList> response) {
                if(response.isSuccessful()){
                    Booklist=response.body();
                    Log.d("TEST", "성공!!");

                    //recyclerview //리콤 top5
                    firstInit();

                    int imax = 5;
                    int count = 0;
                    for(int i=0;i<imax;i++){
                        Log.d("TESTURL",Booklist.getItem().get(i).getCoverLargeUrl());
                        if(!(Booklist.getItem().get(i).getCoverLargeUrl().contains("/partner/"))){
                            imax++;
                            continue;
                        }else{ count++;}
                        addItem(Booklist.getItem().get(i).getCoverLargeUrl(), count, Booklist.getItem().get(i).getTitle(), Booklist.getItem().get(i).getAuthor(), Booklist.getItem().get(i).getCategoryName(), "카테고리2");
                    }
                    mRecyclerViewAdapter = new RecyclerViewAdapter(mList, MainActivity.this);
                    mRecyclerView.setAdapter(mRecyclerViewAdapter);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false)); //가로
                }
            }

            @Override
            public void onFailure(Call<BookList> call, Throwable t) {
                t.printStackTrace();
                Log.d("TEST","실패");
            }
        });

        //viewpager2 //배너
        sliderViewPager = findViewById(R.id.sliderViewPager);
        layoutIndicator = findViewById(R.id.layoutIndicators);

        sliderViewPager.setOffscreenPageLimit(1);
        sliderViewPager.setAdapter(new ImageSliderAdapter(this, images));

        sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });

        setupIndicators(images.length);




    }

    //리콤 top5
    public void firstInit(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mList = new ArrayList<>();
    }
    //리콤 top5
    public void addItem(String imgName,Integer rank, String titleText, String writerText, String category1, String category2){
        RecyclerViewItem item = new RecyclerViewItem();
        
        item.setMainImg(imgName);
        item.setRank(rank);
        item.setTitle(titleText);
        item.setWriter(writerText);
        item.setCategory(category1);
        item.setCategory2(category2);

        mList.add(item);
    }
    //배너
    private void setupIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(16, 8, 16, 8);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(this,
                    R.drawable.slider2));
            indicators[i].setLayoutParams(params);
            layoutIndicator.addView(indicators[i]);
        }
        setCurrentIndicator(0);
    }
    //배너
    private void setCurrentIndicator(int position) {
        int childCount = layoutIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.slider
                ));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.slider2
                ));
            }
        }
    }
}