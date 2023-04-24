package com.example.recomm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 sliderViewPager, sliderViewPager2;
    private LinearLayout layoutIndicator, layoutIndicator2;

    private int[] images = new int[] {
            R.drawable.img1,
            R.drawable.img3,
            R.drawable.img2
    };
    private int[] images2 = new int[] {
            0,
            R.drawable.img3_2,
            0
    };
    private String[] texts = new String[] {
            "이 드라마가 \n" + "소설 원작이라고?",
            "모든 것은 \n" + "용기의 문제다",
            "영화를 만들어낸 토대가\n" + "이 한 권에 담겼다"
    };
    private String[] texts2 = new String[] {
            "SBS ‘사랑의 온도’ 원작 소설을 \n" + "리콤에서 최저가로 알려드립니다.",
            "따뜻함을 줄 신작 ‘미움 받을 용기'를\n" + "리콤에서 최저가로 알려드립니다.  ",
            "이노우에 타케히코가 영화를 제작하는 과정을 \n" + "담은 글과 그림, 인터뷰 등에 관한 이야기를\n" + "리콤에서 최저가로 알려드립니다."
    };
    private int[] images2_2= new int[]{
            R.drawable.testimg,
            R.drawable.testimg,
            R.drawable.testimg
    };
    private int[] images_2= new int[]{
            R.drawable.testimg,
            0,
            0
    };
    private String[] texts_2= new String[]{
            "사건이 발생하고\n" + "추리가 시작되었다. ",
            "나 있는 그대로 참 좋다",
            "착하게 그러나 단호하게"
    };
    private String[] texts2_2= new String[]{
            "",
            "자신이 얼마나 아름다운지 모르는 \n나에게 필요한 마음 주문",
            "당신의 착함을 이용하는 사람들에게 \n먹이는 한 방"
    };

    private RecyclerView mRecyclerView, mRecyclerView2;
    private ArrayList<RecyclerViewItem> mList;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private ArrayList<Recomm_RecyclerViewItem> mList2;
    private Recomm_RecyclerViewAdapter recommRecyclerViewAdapter;
    private ImageButton top5Btn;
    private LinearLayout imgl, textl;

    @SuppressLint("MissingInflatedId")
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
                    BookList  Booklist=response.body();
                    Log.d("TEST", "성공!!");

                    //recyclerview //리콤 top5
                    firstInit(R.id.recyclerView);

                    int imax = 5;
                    int count = 0;
                    for(int i=0;i<imax;i++){
                        Log.d("TESTURL",Booklist.getItem().get(i).getCoverLargeUrl());
                        if(!(Booklist.getItem().get(i).getCoverLargeUrl().contains("/partner/"))){
                            imax++;
                            continue;
                        }else{ count++;}
                        addItem(Booklist.getItem().get(i).getCoverLargeUrl(), count, Booklist.getItem().get(i).getTitle(), Booklist.getItem().get(i).getAuthor(), Booklist.getItem().get(i).getCategoryName(), "카테고리2", mList);
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
        retrofitAPI.getSearchData("45B4DD127DD3DD5D8A37DCB8810027A5266CC3A45E174E40D8E0A6117008717E","김호연", "author","101","json").enqueue(new Callback<BookList>() {
            @Override
            public void onResponse(Call<BookList> call, Response<BookList> response) {
                if(response.isSuccessful()){
                    Log.d("TEST", "성공!!");

                    //recyclerview //리콤 top5
                    secondInit(R.id.recyclerView2);
                    BookList b = response.body();
                    int imax = 5;
                    for(int i=0;i<imax;i++){
                        if(!(b.getItem().get(i).getAuthor().equals("김호연"))){
                            continue;
                        }addItem2(b.getItem().get(i).getCoverLargeUrl(), b.getItem().get(i).getTitle(), b.getItem().get(i).getAuthor(), mList2);
                    }
                    recommRecyclerViewAdapter = new Recomm_RecyclerViewAdapter(mList2, MainActivity.this);
                    mRecyclerView2.setAdapter(recommRecyclerViewAdapter);
                    mRecyclerView2.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    mRecyclerView2.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false)); //가로
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
        sliderViewPager.setAdapter(new ImageSliderAdapter(this, images, texts, texts2, images2));

        sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(layoutIndicator,position);
            }
        });
        setupIndicators(layoutIndicator,images.length);

        //viewpager2 //광고 슬라이드
        sliderViewPager2 = findViewById(R.id.viewPager2);
        layoutIndicator2 = findViewById(R.id.linearLayout4);

        sliderViewPager2.setOffscreenPageLimit(1);
        sliderViewPager2.setAdapter(new Viewpager2Adapter2(images_2, images2_2, texts_2, texts2_2));

        sliderViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(layoutIndicator2,position);
            }
        });
        setupIndicators(layoutIndicator2,images2.length);

        //top5 더보기 버튼
        top5Btn = findViewById(R.id.top5Btn);
        top5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecommRank.class);
                startActivity(intent);
            }
        });

    }

    //리콤 top5
    public void firstInit(int view){
        mRecyclerView = (RecyclerView) findViewById(view);
        mList = new ArrayList<>();
    }
    //사용자 맞춤 추천
    public void secondInit(int view){
        mRecyclerView2 = (RecyclerView) findViewById(view);
        mList2 = new ArrayList<>();
    }
    //리콤 top5
    public void addItem(String imgName,Integer rank, String titleText, String writerText, String category1, String category2 , ArrayList<RecyclerViewItem> list){
        RecyclerViewItem item = new RecyclerViewItem();
        
        item.setMainImg(imgName);
        item.setRank(rank);
        item.setTitle(titleText);
        item.setWriter(writerText);
        item.setCategory(category1);
        item.setCategory2(category2);

        list.add(item);
    }
    //사용자 맞춤 추천
    public void addItem2(String imgName, String titleText, String writerText, ArrayList<Recomm_RecyclerViewItem> list){
        Recomm_RecyclerViewItem item = new Recomm_RecyclerViewItem();

        item.setMainImg(imgName);
        item.setTitle(titleText);
        item.setWriter(writerText);

        list.add(item);
    }
    //배너
    private void setupIndicators(LinearLayout lin, int count) {
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(16, 8, 16, 8);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(this,
                    R.drawable.slider2));
            indicators[i].setLayoutParams(params);
            lin.addView(indicators[i]);
        }
        setCurrentIndicator(lin,0);
    }
    //배너
    private void setCurrentIndicator(LinearLayout lin, int position) {
        int childCount = lin.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) lin.getChildAt(i);
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