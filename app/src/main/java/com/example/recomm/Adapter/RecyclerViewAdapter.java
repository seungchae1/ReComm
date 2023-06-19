package com.example.recomm.Adapter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recomm.BookDetail;
import com.example.recomm.BookList;
import com.example.recomm.Model.Book;
import com.example.recomm.R;
import com.example.recomm.Search;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private FragmentManager fmanager;
    private BookList bookList;
    private String userId = "";

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mainImg, backImg;
        TextView rankText, titleText, writerText, categoryText, categoryText2;
        ConstraintLayout backLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            backLayout= (ConstraintLayout) itemView.findViewById(R.id.Mainlayout);
            backImg = (ImageView) itemView.findViewById(R.id.back_imgv);
            mainImg = (ImageView) itemView.findViewById(R.id.main_imgv);
            rankText = (TextView) itemView.findViewById(R.id.rank_text);
            titleText = (TextView) itemView.findViewById(R.id.title_text);
            writerText = (TextView) itemView.findViewById(R.id.writer_text);
            categoryText = (TextView) itemView.findViewById(R.id.category1);
            categoryText2 = (TextView) itemView.findViewById(R.id.category2);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    RecyclerViewItem myitem = mList.get(position);
                    Book b = bookList.getItem().get(position);
                    Log.d("test3", myitem.getWriter());
                    Bundle bundle = new Bundle();
                    bundle.putString("img", myitem.getMainImg());
                    bundle.putString("writer", myitem.getWriter());
                    bundle.putString("title", myitem.getTitle());
                    bundle.putString("desc",b.getDescription());
                    bundle.putInt("book_id",b.getItemId());
                    bundle.putString("userid",userId);
                    BookDetail targetFragment = new BookDetail();
                    targetFragment.setArguments(bundle);
                    fmanager.beginTransaction().add(R.id.frame, targetFragment).commit();
                }
            });
        }
    }

    private ArrayList<RecyclerViewItem> mList = null;

    public RecyclerViewAdapter(ArrayList<RecyclerViewItem> mList, Context context, FragmentManager fragmentManager, BookList bookList, String userId) {
        this.mList = mList;
        this.context = context;
        fmanager = fragmentManager;
        this.bookList = bookList;
        this.userId = userId;
        Log.d("TEst", userId);
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_recycler_item, parent, false);
        RecyclerViewAdapter.ViewHolder vh = new RecyclerViewAdapter.ViewHolder(view);
        return vh;
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        RecyclerViewItem item = mList.get(position);
        Log.d("test1", item.getTitle());
        //holder.backImg.setBackgroundResource(R.drawable.testimg);
        Glide.with(context)
                .load(item.getMainImg())
                .diskCacheStrategy(DiskCacheStrategy.NONE) // 캐시 삭제
                .skipMemoryCache(true) // 캐시 삭제  
                //.placeholder(R.drawable.testimg) // 로딩중 이미지
                //.error(R.drawable.testimg) // 로드 실패 이미지
                .into(holder.backImg);
        //holder.mainImg.setImageResource(R.drawable.testimg);

        Glide.with(context)
                .load(item.getMainImg())
                .diskCacheStrategy(DiskCacheStrategy.NONE) // 캐시 삭제
                .skipMemoryCache(true) // 캐시 삭제
                //.placeholder(R.drawable.testimg) // 로딩중 이미지
                //.error(R.drawable.testimg) // 로드 실패 이미지
                .into(holder.mainImg);
        holder.rankText.setText(item.getRank().toString());
        holder.titleText.setText(item.getTitle());
        holder.writerText.setText(item.getWriter());
        holder.categoryText.setText(item.getCategory());
        //holder.categoryText2.setText(item.getCategory2().toString());
        Log.d("test2", item.getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
