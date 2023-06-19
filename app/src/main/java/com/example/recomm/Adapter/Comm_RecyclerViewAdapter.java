package com.example.recomm.Adapter;

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
import com.example.recomm.Model.Comm;
import com.example.recomm.R;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Comm_RecyclerViewAdapter extends RecyclerView.Adapter<Comm_RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private FragmentManager fmanager;
    private ArrayList<Comm> commList;
    private String userId = "";

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView user, Content, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user = (TextView) itemView.findViewById(R.id.user_id);
            Content = (TextView) itemView.findViewById(R.id.comm_content);
            date = (TextView) itemView.findViewById(R.id.comm_date);
        }
    }

    public Comm_RecyclerViewAdapter(Context context, FragmentManager fragmentManager, ArrayList<Comm> commList, String userId) {
        this.context = context;
        fmanager = fragmentManager;
        this.commList = commList;
        this.userId = userId;
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_comm, parent, false);
        Comm_RecyclerViewAdapter.ViewHolder vh = new Comm_RecyclerViewAdapter.ViewHolder(view);
        return vh;
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull Comm_RecyclerViewAdapter.ViewHolder holder, int position) {
        Comm item = commList.get(position);
        holder.user.setText(item.getUserId());
        holder.Content.setText(item.getContent());
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        //String formattedTime = sdf.format(Date.valueOf(item.getDate()));
        holder.date.setText(item.getDate());
    }

    @Override
    public int getItemCount() {
        return commList.size();
    }

}
