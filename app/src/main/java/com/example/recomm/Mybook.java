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
        String userId = bundle.getString("email");
        DatabaseReference db = mRootRef.child("user").child(userId).child("mypost");

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
                    Log.d("bookid", s.child("bookId").getValue().toString());
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
                                Log.d("test", bookList.size()+" "+cnt);
                                recyclerView = view.findViewById(R.id.recyclerView);
                                adapter = new myComm_RecyclerViewAdapter(getActivity(), getParentFragmentManager(), bookList, myComm);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            }
                        }

                        @Override
                        public void onFailure(Call<BookList> call, Throwable t) {
                            Log.d("TEST","실패");
                            t.printStackTrace();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TEST","실패");
            }
        });

        return view;
    }
}