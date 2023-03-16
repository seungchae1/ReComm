package com.example.recomm;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewPager2Adapter  extends RecyclerView.Adapter<ViewPager2Adapter.MyViewHolder>
{
    private TypedArray mItems; // drawable 경로 데이터 저장 배열
    private Context context;

    public ViewPager2Adapter(Context context, TypedArray mItems){
        this.context = context;
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public ViewPager2Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slide_imgview, parent, false);
        return new MyViewHolder(view);
    }

    //이미지 교체 함수
    @Override
    public void onBindViewHolder(@NonNull ViewPager2Adapter.MyViewHolder holder, int position) {
        int index =position % mItems.length();
        Log.d("MyAdapter", "onBindViewHolder index : "+index);
        if(index >= mItems.length()){
            index =0;
        }
        holder.imageView.setImageResource(mItems.getResourceId(index,-1));
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
