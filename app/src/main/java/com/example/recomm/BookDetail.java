package com.example.recomm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.recomm.Adapter.Comm_RecyclerViewAdapter;
import com.example.recomm.Adapter.Recomm_RecyclerViewAdapter;
import com.example.recomm.Model.Comm;
import com.example.recomm.databinding.FragmentBookDetailBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.http.Url;

public class BookDetail extends Fragment {

    // 리뷰 탭뷰
    private RelativeLayout latestLayout;
    private RelativeLayout recommendLayout;
    private RelativeLayout myReviewLayout;
    private LinearLayout textBox; // 이거는 뭐가 잘못된 건지 보이면 안되는데 보여서 쓰는 코드니까 무시하면돼!!!

    private ImageView img, img2;
    private TextView title, writer, desc;

    private View view;

    private EditText CommText;
    private ImageButton CommSubmit;
    private String userId;
    private int BookId;

    private RecyclerView recyclerView;
    private long user_index, post_index;

    private FragmentBookDetailBinding binding;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference(); //fire base
    DatabaseReference user_db = mRootRef.child("user");
    DatabaseReference post_db = mRootRef.child("post");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_book_detail, container, false);

        latestLayout = view.findViewById(R.id.latestLayout);
        recommendLayout = view.findViewById(R.id.recommendLayout);
        myReviewLayout = view.findViewById(R.id.myReviewLayout);
        textBox = view.findViewById(R.id.textBox); // 무시히ㅏ면돼!!!

        img = view.findViewById(R.id.main_img);
        img2 = view.findViewById(R.id.back_img);

        title = view.findViewById(R.id.title);
        writer = view.findViewById(R.id.writername);
        desc = view.findViewById(R.id.book_desc);

        recyclerView = view.findViewById(R.id.recyclerView);

        //해당 도서 데이터
        Bundle bundle = getArguments();
        if(bundle != null){
            Glide.with(this)
                .load(bundle.getString("img"))
                .into(img);
            Glide.with(this)
                    .load(bundle.getString("img"))
                    .into(img2);
            title.setText(bundle.getString("title"));
            writer.setText(bundle.getString("writer"));
            desc.setText(bundle.getString("desc"));

            //user
            userId = bundle.getString("userid");
            BookId = bundle.getInt("book_id");
        }


        //리뷰 recyclerview
        setIndex();
        setRecycle();

        //리뷰 작성
        CommText = view.findViewById(R.id.comm_text);
        CommSubmit = view.findViewById(R.id.comm_submin);
        CommSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db에 저장
                Date currentTime = new Date();
                Comm comm = new Comm();
                comm.setBookId(BookId);
                comm.setContent(CommText.getText().toString());
                comm.setUserId(userId);
                comm.setDate(String.valueOf(currentTime));
                user_db.child(userId).child("mypost").child(String.valueOf(user_index)).setValue(comm);
                post_db.child(String.valueOf(BookId)).child(String.valueOf(post_index)).setValue(comm);
                CommText.setText("");
                setIndex();
                setRecycle();
            }
        });

        // 작품 정보 탭뷰
        CustomTab customTab = new CustomTab();
        customTab.init();

        return view;
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
            item1 = view.findViewById(R.id.item1);
            item1Layout = view.findViewById(R.id.item1Layout);
            item2Layout = view.findViewById(R.id.item2Layout);
            item2 = view.findViewById(R.id.item2);
            item1.setOnClickListener(this);
            item2.setOnClickListener(this);
            select = view.findViewById(R.id.select);
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

    public void setIndex(){
        DatabaseReference index = mRootRef.child("user").child(userId).child("mypost");
        DatabaseReference index2 = mRootRef.child("post").child(String.valueOf(BookId));
        index.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user_index = snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        index2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                post_index = snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setRecycle(){
        ArrayList<Comm> commList = new ArrayList<Comm>();
        post_db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s : snapshot.getChildren()){
                    if(s.getKey().equals(String.valueOf(BookId))){
                        for(int i=0; i<post_index; i++) {
                            Comm comm = new Comm();
                            comm.setUserId(s.child(String.valueOf(i)).child("userId").getValue().toString());
                            comm.setBookId(Integer.parseInt(s.child(String.valueOf(i)).child("bookId").getValue().toString()));
                            comm.setContent(s.child(String.valueOf(i)).child("content").getValue().toString());
                            comm.setDate(s.child(String.valueOf(i)).child("date").getValue().toString());
                            Log.d("comm test", comm.getUserId());
                            commList.add(comm);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
       Comm_RecyclerViewAdapter recommRecyclerViewAdapter = new Comm_RecyclerViewAdapter(getActivity(), getParentFragmentManager(), commList, userId);
       recyclerView.setAdapter(recommRecyclerViewAdapter);
       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
