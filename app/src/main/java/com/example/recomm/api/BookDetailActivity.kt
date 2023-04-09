package com.example.recomm.api

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.recomm.R
import retrofit2.Call
import retrofit2.Response
import java.nio.file.Path

class BookDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_detail_activity)
        val BookName = intent.getStringExtra("BookName")
        val BookcoverLargeUrl = intent.getStringExtra("BookcoverLargeUrl")
        val Bookdescription = intent.getStringExtra("Bookdescription")
        val BookNum = intent.getIntExtra("BookNum", 10)

        val name = findViewById<TextView>(R.id.book_name)
        val coverLargeUrl = findViewById<ImageView>(R.id.book_coverLargeUrl)
        val description = findViewById<TextView>(R.id.book_description)

        Log.d("bb", BookNum.toString())

        if(BookNum>10){
            name.setTextSize(40.0f)
        }

        name.text = BookName.toString()
        //coverLargeUrl.text = BookcoverLargeUrl.toString()
        description.text = Bookdescription.toString()


        Glide
            .with(this)
            .load(BookcoverLargeUrl)
            .centerCrop()
            //.placeholder(R.drawable.loading_spinner)
            .into(coverLargeUrl)


        Log.d("a", BookName.toString())
        Log.d("a", BookcoverLargeUrl.toString())
        Log.d("a", Bookdescription.toString())

    }}