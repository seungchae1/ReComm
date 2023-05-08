package com.example.recomm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Viewpager2Adapter2 extends RecyclerView.Adapter<Viewpager2Adapter2.MyViewHolder> {
    private int[] sliderImage;
    private String[] sliderText;
    private String[] sliderText2;
    private int[] sliderImage2;


    public Viewpager2Adapter2(int[] sliderImage, int[] sliderImage2, String[] sliderText,String[] sliderText2) {
        this.sliderImage = sliderImage;
        this.sliderText = sliderText;
        this.sliderText2 = sliderText2;
        this.sliderImage2 = sliderImage2;
    }

    @NonNull
    @Override
    public Viewpager2Adapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewpager2_item, parent, false);
        return new Viewpager2Adapter2.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewpager2Adapter2.MyViewHolder holder, int position) {
        holder.bindSliderImage(sliderImage[position], sliderImage2[position], sliderText[position], sliderText2[position]);
    }

    @Override
    public int getItemCount() {
        return sliderImage.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mimg;
        private ImageView mimg2;
        private TextView mTextView2;
        private TextView mTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mimg = itemView.findViewById(R.id.img1);
            mimg2 = itemView.findViewById(R.id.img2);
            mTextView = itemView.findViewById(R.id.text1);
            mTextView2 = itemView.findViewById(R.id.text2);
        }

        public void bindSliderImage(int img, int img2, String text, String text2) {
            mimg.setImageResource(img);
            mimg2.setImageResource(img2);
            if(img == 0){
            }
            mTextView.setText(text);
            mTextView2.setText(text2);
        }
    }
}