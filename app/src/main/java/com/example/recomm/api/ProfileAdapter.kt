package com.example.recomm.api


import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recomm.R
import java.io.Console

class ProfileAdapter(val dataList: List<Book>) :
    RecyclerView.Adapter<ProfileAdapter.BookItemHolder>() {
    class BookItemHolder(val view: View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return BookItemHolder(view)
    }

    override fun onBindViewHolder(holder: BookItemHolder, position: Int) {
        val BookName = holder.view.findViewById<TextView>(R.id.book_name)
        BookName.text = dataList[position].title
        val bookCoverSmallUrl = dataList[position].coverSmallUrl

        holder.itemView.setOnClickListener {
            //Log.d("asdf", dataList[position].toString());

            val nextIntent = Intent(holder.view.context, BookDetailActivity::class.java)
            nextIntent.putExtra("BookName", dataList[holder.adapterPosition].title)
            nextIntent.putExtra("BookcoverLargeUrl", dataList[holder.adapterPosition].coverLargeUrl)
            nextIntent.putExtra("Bookdescription", dataList[holder.adapterPosition].description)
            nextIntent.putExtra("BookNum", dataList[holder.adapterPosition].title.length)

            holder.view.context.startActivity(nextIntent)
        }






        Glide
            .with(holder.view.context)
            .load(bookCoverSmallUrl)
            .centerCrop()
            //.placeholder(R.drawable.loading_spinner)
            .into(holder.view.findViewById<ImageView>(R.id.book_img))





    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener



    override fun getItemCount(): Int = dataList.size


    override fun getItemViewType(position: Int): Int {
        return R.layout.activity_main
    }

    /*
    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        /*
        cardView?.setOnClickListener { View ->
            Toast.makeText( "카드뷰 클릭됨", Toast.LENGTH_SHORT).show()
            Log.e("check", "카드뷰 클릭")
            val link = Book[bindingAdapterPosition]
            Intent(BookDetailActivity::class.java).apply {
                putExtra("imageUri", link)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { startActivity(this) }}
        */
*/
}



