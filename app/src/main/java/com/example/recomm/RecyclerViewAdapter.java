package com.example.recomm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
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
        }
    }

    private ArrayList<RecyclerViewItem> mList = null;

    public RecyclerViewAdapter(ArrayList<RecyclerViewItem> mList) {
        this.mList = mList;
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

        holder.backImg.setBackgroundResource(R.drawable.testimg);
        holder.mainImg.setImageResource(R.drawable.testimg);
        holder.rankText.setText(item.getRank().toString());
        holder.titleText.setText(item.getTitle().toString());
        holder.writerText.setText(item.getWriter().toString());
        holder.categoryText.setText(item.getCategory().toString());
        holder.categoryText2.setText(item.getCategory2().toString());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}