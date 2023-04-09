package com.example.recomm.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("/api/search.api?output=json")
    fun getBooksByName(
        @Query("key") apiKey: String,
        @Query("query") keyword: String
    ): Call<SearchBookDto>
    //return형은 Call 반환타입은 SearchBookDto

    @GET("/api/bestSeller.api?output=json&categotyId=100")
    fun getBestSellerBooks(
        @Query("key") apiKey: String
    ): Call<Book>
}

data class Book(
    // item 배열의 요소 속 id들
    @SerializedName("itemId") val id: Long, //itemId요소를 가져와 id라는 변수에 싱크 해준다
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("coverSmallUrl") val coverSmallUrl: String,
    @SerializedName("coverLargeUrl") val coverLargeUrl: String
)

data class SearchBookDto(
    // 제일 처음 데이터 요청 시 title 값과 item 목록을 받아온다
    @SerializedName("title") val title: String,
    @SerializedName("item") val bookList: List<Book> // Book 형태의 List로 받아온다.
)