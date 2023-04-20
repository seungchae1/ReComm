package com.example.recomm;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.w3c.dom.Text;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.MyViewHolder> {
    private Context context;
    private int[] sliderImage;
    private String[] sliderText;
    private String[] sliderText2;
    private int[] sliderImage2;


    public ImageSliderAdapter(Context context, int[] sliderImage, String[] sliderText,String[] sliderText2, int[] sliderImage2) {
        this.context = context;
        this.sliderImage = sliderImage;
        this.sliderText = sliderText;
        this.sliderText2 = sliderText2;
        this.sliderImage2 = sliderImage2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_slider, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindSliderImage(sliderImage[position], sliderText[position], sliderText2[position], sliderImage2[position]);
    }

    @Override
    public int getItemCount() {
        return sliderImage.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private androidx.constraintlayout.widget.ConstraintLayout mlin;
        private TextView mTextView2;
        private TextView mTextView;
        private ImageView mimg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mlin = itemView.findViewById(R.id.back);
            mTextView = itemView.findViewById(R.id.text);
            mTextView2 = itemView.findViewById(R.id.text2);
            mimg = itemView.findViewById(R.id.img2);
        }

        public void bindSliderImage(int imageURL, String text, String text2, int img2) {
            mlin.setBackgroundResource(imageURL);
            mimg.setImageResource(img2);
            mTextView.setText(text);
            mTextView2.setText(text2);
        }
    }
}