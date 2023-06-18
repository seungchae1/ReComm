package com.example.recomm.Adapter;

import android.content.Context;
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
import com.example.recomm.R;
import com.example.recomm.RecommRank;

import java.util.ArrayList;

public class Recomm_RecyclerViewAdapter  extends RecyclerView.Adapter<Recomm_RecyclerViewAdapter.ViewHolder> {
    private Context context;
    FragmentManager fragmentManager;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mainImg;
        TextView titleText, writerText;
        ConstraintLayout backLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            backLayout= (ConstraintLayout) itemView.findViewById(R.id.Mainlayout);
            mainImg = (ImageView) itemView.findViewById(R.id.main_imgv);
            titleText = (TextView) itemView.findViewById(R.id.title_text);
            writerText = (TextView) itemView.findViewById(R.id.writer_text);
        }
    }

    private ArrayList<Recomm_RecyclerViewItem> mList = null;

    public Recomm_RecyclerViewAdapter(ArrayList<Recomm_RecyclerViewItem> mList, Context context, FragmentManager fragmentManager) {
        this.mList = mList;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public Recomm_RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recomm_recycler_item, parent, false);
        Recomm_RecyclerViewAdapter.ViewHolder vh = new Recomm_RecyclerViewAdapter.ViewHolder(view);

        return vh;
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull Recomm_RecyclerViewAdapter.ViewHolder holder, int position) {
        Recomm_RecyclerViewItem item = mList.get(position);

        Glide.with(context)
                .load(item.getMainImg())
                .diskCacheStrategy(DiskCacheStrategy.NONE) // 캐시 삭제
                .skipMemoryCache(true) // 캐시 삭제
                .placeholder(R.drawable.testimg) // 로딩중 이미지
                .error(R.drawable.testimg) // 로드 실패 이미지
                .into(holder.mainImg);
        holder.titleText.setText(item.getTitle().toString());
        holder.writerText.setText(item.getWriter().toString());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void onItemClick(View view, int position) {
        fragmentManager.beginTransaction().replace(R.id.frame, new BookDetail()).commit();
    }
}