package com.example.recomm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import org.w3c.dom.Text;

public class JoinQuestion extends AppCompatActivity {
    HorizontalScrollView hori;
    GridView gridV;
    String selCategory[] = {
            "가정 살림", "IT 모바일", "에세이", "여행", "역사", "시리즈", "정치",
            "인물", "소설/시/희극", "잡지", "취미", "인문", "외국어 사전", "유아/어린이", "청소년", "스포츠/건강",
            "만화", "자연과학", "예술", "경제 경영", "종교", "국어사전", "참고서/교재", "음악"
    };
    String selGenre[]={
            "로맨스", "사극", "시리즈", "공포", "스릴러", "판타지", "코미디", "BL/GL"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_question);
        
        //책 카테고리
        hori = findViewById(R.id.horiView);
        LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout linear2= new LinearLayout(this);;
        for(int i =0; i<selCategory.length; i++){
            if(i%8==0 || i==0){
                linear2 = new LinearLayout(this);
                linear2.setOrientation(LinearLayout.HORIZONTAL);
                linear2.setPadding(0,35,0,0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                linear2.setLayoutParams(params);
            }
            if(i%8==0){
                linear.addView(linear2);
            }
            TextView tv = new TextView(JoinQuestion.this);
            tv.setText(selCategory[i]);
            tv.setBackgroundResource(R.drawable.linerectangle2);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.rightMargin=25;
            tv.setLayoutParams(params);
            tv.setPadding(55,23,55,23);
            linear2.addView(tv);
        }
        hori.addView(linear);

        //책 장르
        gridV = findViewById(R.id.genreGrid);
        textAdapter adapter = new textAdapter(this);
        gridV.setAdapter(adapter);
    }

    private class textAdapter extends BaseAdapter{
        Context context;
        public textAdapter(Context context){
            this.context = context;
        }
        @Override
        public int getCount() {
            return selGenre.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            TextView tv = new TextView(context);
            tv.setText(selGenre[i]);
            tv.setBackgroundResource(R.drawable.linerectangle2);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tv.setGravity(1);
            tv.setLayoutParams(params);
            tv.setPadding(0,23,0,23);
            return tv;
        }
    }
}