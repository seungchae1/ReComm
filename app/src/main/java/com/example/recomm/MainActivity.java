package com.example.recomm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.recomm.Model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottom;
    OnBackPressedListener listener;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String pass = intent.getStringExtra("pass");
        String birth = intent.getStringExtra("birth");
        bottom = findViewById(R.id.navigationView);
        bottom.setSelectedItemId(R.id.navagation_home);
        bundle = new Bundle();
        //test
        email = "dtest";
        bundle.putString("name",name);
        bundle.putString("pass",pass);
        bundle.putString("birth",birth);
        bundle.putString("email",email);
        Home targetFragment = new Home();
        targetFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.frame, targetFragment).commit();

        bottom.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navagation_category:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.frame, new MainFragment()).commit();
                        break;
                    case R.id.navagation_search:
                        Search targetFragment2 = new Search();
                        targetFragment2.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().add(R.id.frame, targetFragment2).commit();
                        break;
                    case R.id.navagation_home:
                        Home targetFragment3 = new Home();
                        targetFragment3.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().add(R.id.frame, targetFragment3).commit();
                        break;
                    case R.id.navagation_mybook:
                        Mybook targetFragment4 = new Mybook();
                        targetFragment4.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().add(R.id.frame, targetFragment4).commit();
                        break;
                    case R.id.navagation_mypage:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Mypage()).commit();
                        break;
                }

                return true;
            }
        });
    }

//    public void changeFragment(int index) {
//        switch (index) {
//            case 1:
//                getSupportFragmentManager().beginTransaction()
//                       // .replace(R.id.frame, new MyPlan())
//                        .commit();
//                break;
//        }
//    }
//
//    public void setOnBackPressedListener(OnBackPressedListener listener){
//        this.listener = listener;
//    }
//
//    @Override
//    public void onBackPressed() {
//        if(listener!=null){
//            listener.onBackPressed();
//        }else{
//            super.onBackPressed();
//        }
//    }
//
//    public void endActivity() {
//        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        startActivity(intent);
//        finish();
//    }
//
//    public FragmentManager getFragmentMana() {
//        return getSupportFragmentManager();
//    }
//
//    public void replaceFragment(Fragment fragment) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
//
//    public void replacePost(int index) {
//        Fragment fragment = TripPost1.newInstance();
//        switch (index) {
//            case 2:
//                fragment = TripPost2.newInstance();
//                break;
//            case 3:
//                fragment = TripPost3.newInstance();
//                break;
//            case 4:
//                fragment = TripPost4.newInstance();
//                break;
//        }
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
}
