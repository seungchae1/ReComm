package com.example.recomm.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recomm.Model.Book;
import com.example.recomm.Model.Comm;
import com.example.recomm.R;

import java.util.ArrayList;

public class myComm_RecyclerViewAdapter extends RecyclerView.Adapter<myComm_RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private FragmentManager fmanager;
    private ArrayList<Book> commList;
    private ArrayList<String> myComm;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, writer, content;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            writer = (TextView) itemView.findViewById(R.id.writer);
            content = (TextView) itemView.findViewById(R.id.content);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }

    public myComm_RecyclerViewAdapter(Context context, FragmentManager fragmentManager, ArrayList<Book> commList, ArrayList<String> myComm) {
        this.context = context;
        fmanager = fragmentManager;
        this.commList = commList;
        this.myComm = myComm;
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.mycomm_item, parent, false);
        myComm_RecyclerViewAdapter.ViewHolder vh = new myComm_RecyclerViewAdapter.ViewHolder(view);
        return vh;
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull myComm_RecyclerViewAdapter.ViewHolder holder, int position) {
        Book item = commList.get(position);
        holder.title.setText(item.getTitle());
        holder.writer.setText(item.getAuthor());
        holder.content.setText(myComm.get(position));
        Glide.with(context)
                .load(item.getCoverLargeUrl())
                .diskCacheStrategy(DiskCacheStrategy.NONE) // 캐시 삭제
                .skipMemoryCache(true)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return myComm.size();
    }

}
