package com.example.recomm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class JoinQuestion extends AppCompatActivity {
    HorizontalScrollView hori;
    String selCategory[] = {
            "가정 살림", "IT 모바일", "에세이", "여행", "역사", "시리즈", "정치",
            "인물", "소설/시/희극", "잡지", "취미", "인문", "외국어 사전", "유아/어린이", "청소년", "스포츠/건강",
            "만화", "자연과학", "예술", "경제 경영", "종교", "국어사전", "참고서/교재", "음악"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_question);
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
    }
}