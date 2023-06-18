package com.example.recomm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recomm.Adapter.Recomm_RecyclerViewAdapter;
import com.example.recomm.Adapter.Recomm_RecyclerViewItem;
import com.example.recomm.Adapter.RecyclerViewAdapter;
import com.example.recomm.Adapter.RecyclerViewItem;
import com.example.recomm.Adapter.RecyclerView_Rank;
import com.example.recomm.Adapter.RecyclerView_Rank_2;
import com.example.recomm.databinding.FragmentHomeBinding;
import com.example.recomm.databinding.FragmentRecommRankBinding;
import com.example.recomm.databinding.RecommRank5Binding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecommRank extends Fragment {

    private RecyclerView mRecyclerView, mRecyclerView2;
    private ArrayList<RecyclerViewItem> mList, mlist2;
    private RecyclerView_Rank mRecyclerViewAdapter;
    private RecyclerView_Rank_2 mRecyclerViewAdapter2;
    private FragmentRecommRankBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recomm_rank, container, false);
        binding = FragmentRecommRankBinding.bind(view);
        Log.d("TEST","RecommRank 시작");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://book.interpark.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getData("45B4DD127DD3DD5D8A37DCB8810027A5266CC3A45E174E40D8E0A6117008717E","101","json").enqueue(new Callback<BookList>() {
            @Override
            public void onResponse(Call<BookList> call, Response<BookList> response) {
                if (response.isSuccessful()) {
                    BookList Booklist = response.body();
                    Log.d("TEST", "성공!!");

                    mList = new ArrayList<>();
                    mlist2 = new ArrayList<>();

                    int imax = 25;
                    int count = 0;
                    for (int i = 0; i < imax; i++) {
                        Log.d("TESTURL", Booklist.getItem().get(i).getCoverLargeUrl());
                        if (!(Booklist.getItem().get(i).getCoverLargeUrl().contains("/partner/"))) {
                            imax++;
                            continue;
                        } else {
                            count++;
                        }
                        if(i<5) addItem(Booklist.getItem().get(i).getCoverLargeUrl(), count, Booklist.getItem().get(i).getTitle(), Booklist.getItem().get(i).getAuthor(), Booklist.getItem().get(i).getCategoryName(), "", mList);
                        else addItem(Booklist.getItem().get(i).getCoverLargeUrl(), count, Booklist.getItem().get(i).getTitle(), Booklist.getItem().get(i).getAuthor(), Booklist.getItem().get(i).getCategoryName(), "", mlist2);
                    }
                    mRecyclerView = binding.recyclerView;
                    mRecyclerView2 = binding.recyclerView2;
                    mRecyclerViewAdapter = new RecyclerView_Rank(mList, getContext());
                    mRecyclerViewAdapter2 = new RecyclerView_Rank_2(mlist2, getContext());
                    mRecyclerView.setAdapter(mRecyclerViewAdapter);
                    mRecyclerView2.setAdapter(mRecyclerViewAdapter2);
                    mRecyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false)); //가로
                }
            }

            @Override
            public void onFailure(Call<BookList> call, Throwable t) {

            }
        });
        return view;
    }

    public void addItem(String imgName,Integer rank, String titleText, String writerText, String category1, String category2 , ArrayList< RecyclerViewItem > list){
        RecyclerViewItem item = new RecyclerViewItem();

        item.setMainImg(imgName);
        item.setRank(rank);
        item.setTitle(titleText);
        item.setWriter(writerText);
        item.setCategory(category1);
        item.setCategory2(category2);

        list.add(item);
    }
}