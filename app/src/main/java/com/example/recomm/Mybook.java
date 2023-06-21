package com.example.recomm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.recomm.Adapter.Recomm_RecyclerViewAdapter;
import com.example.recomm.Adapter.RecyclerViewAdapter;
import com.example.recomm.Adapter.myComm_RecyclerViewAdapter;
import com.example.recomm.Model.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Mybook extends Fragment {
    private RecyclerView recyclerView;
    private myComm_RecyclerViewAdapter adapter;
    private java.util.ArrayList<Book> bookList;
    private ArrayList<String> myComm;
    private String userId;
    ImageView img1, img2, img3, img4, img5, img6;
    ImageView[] imageViews;
    private boolean recyStrart=false;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference(); //fire base

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mybook, container, false);

        myComm = new ArrayList<String>();
        bookList = new ArrayList<Book>();
        Bundle bundle = getArguments();
        userId = bundle.getString("email");
        DatabaseReference db = mRootRef.child("user").child(userId).child("mypost");

        // 최근 본 도서
        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);
        img4 = view.findViewById(R.id.img4);
        img5 = view.findViewById(R.id.img5);
        img6 = view.findViewById(R.id.img6);
        imageViews = new ImageView[]{img1, img2, img3, img4, img5, img6};

        DatabaseReference view_db = mRootRef.child("user").child(userId).child("view");
        view_db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(int i=0; i<6; i++){
                    Glide.with(getActivity())
                            .load(snapshot.child(String.valueOf(i)).getValue())
                            .into(imageViews[i]);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://book.interpark.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(int i=0; i<snapshot.getChildrenCount(); i++){bookList.add(null);}
                for(DataSnapshot s : snapshot.getChildren()){
                    myComm.add(Integer.parseInt(s.getKey()), s.child("content").getValue().toString());
                    //api
                    retrofitAPI.getSearchData("45B4DD127DD3DD5D8A37DCB8810027A5266CC3A45E174E40D8E0A6117008717E",""+s.child("bookId").getValue(), "productNumber","100","json").enqueue(new Callback<BookList>() {
                        @Override
                        public void onResponse(Call<BookList> call, Response<BookList> response) {
                            BookList b = response.body();
                            Book myb= new Book();
                            myb.setItemId(b.getItem().get(0).getItemId());
                            myb.setAuthor(b.getItem().get(0).getAuthor());
                            myb.setTitle(b.getItem().get(0).getTitle());
                            myb.setCoverLargeUrl(b.getItem().get(0).getCoverLargeUrl());
                            bookList.set(Integer.parseInt(s.getKey()), myb);
                            int cnt=0;
                            for(int i=0; i<snapshot.getChildrenCount(); i++) if(bookList.get(i)!=null) {cnt++;}
                            if(cnt==snapshot.getChildrenCount()) recyStrart = true;
                            if(recyStrart){
                                recyclerView = view.findViewById(R.id.recyclerView);
                                adapter = new myComm_RecyclerViewAdapter(getActivity(), getParentFragmentManager(), bookList, myComm);
                                recyclerView.setAdapter(adapter);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                                //리싸이클러뷰 역순 출력 코드
                                linearLayoutManager.setReverseLayout(true);
                                linearLayoutManager.setStackFromEnd(true);
                                recyclerView.setLayoutManager(linearLayoutManager);
                            }
                        }

                        @Override
                        public void onFailure(Call<BookList> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        return view;
    }
}