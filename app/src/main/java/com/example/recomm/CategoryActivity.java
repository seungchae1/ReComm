package com.example.recomm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recomm.Adapter.CategoryItem;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

public class CategoryActivity extends Fragment {

    private GridView gridView = null;
    private GridViewAdapter adapter = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_category, container, false);

        gridView = (GridView) view.findViewById(R.id.gridView);
        adapter = new GridViewAdapter();

        adapter.addItem(new CategoryItem("가정/취미", R.drawable.icon1));
        adapter.addItem(new CategoryItem("로맨스", R.drawable.icon2));
        adapter.addItem(new CategoryItem("무협/사극", R.drawable.icon3));
        adapter.addItem(new CategoryItem("모든 사전", R.drawable.icon4));
        adapter.addItem(new CategoryItem("만화", R.drawable.icon5));
        adapter.addItem(new CategoryItem("스릴러/공포", R.drawable.icon6));
        adapter.addItem(new CategoryItem("소설", R.drawable.icon7));
        adapter.addItem(new CategoryItem("시/희극", R.drawable.icon8));
        adapter.addItem(new CategoryItem("시대극", R.drawable.icon9));
        adapter.addItem(new CategoryItem("사회/정치", R.drawable.icon10));
        adapter.addItem(new CategoryItem("음악/예술", R.drawable.icon11));
        adapter.addItem(new CategoryItem("인문/에세이", R.drawable.icon12));
        adapter.addItem(new CategoryItem("자연과학", R.drawable.icon13));
        adapter.addItem(new CategoryItem("잡지", R.drawable.icon14));
        adapter.addItem(new CategoryItem("참고서/교재", R.drawable.icon15));
        adapter.addItem(new CategoryItem("추리/범죄", R.drawable.icon16));
        adapter.addItem(new CategoryItem("코미디", R.drawable.icon17));
        adapter.addItem(new CategoryItem("판타지", R.drawable.icon18));
        adapter.addItem(new CategoryItem("BL/GL", R.drawable.icon19));
        adapter.addItem(new CategoryItem("SF", R.drawable.icon20));

        gridView.setAdapter(adapter);
        return view;
    }


    class GridViewAdapter extends BaseAdapter {
        ArrayList<CategoryItem> items = new ArrayList<CategoryItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(CategoryItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View contextView, ViewGroup viewGroup) {
            final Context context = viewGroup.getContext();
            final CategoryItem categoryItem = items.get(position);

            if(contextView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                contextView = inflater.inflate(R.layout.grid_category_item, viewGroup, false);

                ImageView icon = (ImageView) contextView.findViewById(R.id.icon);
                TextView text = (TextView) contextView.findViewById(R.id.text);

                icon.setImageResource(categoryItem.getResId());
                text.setText(categoryItem.getCategory());
            } else {
                View view = new View(context);
                view = (View) contextView;
            }

            contextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, categoryItem.getCategory(), Toast.LENGTH_SHORT).show();
                }
            });

            return contextView;
        }
    }
}