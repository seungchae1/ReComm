package com.example.recomm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("/api/bestSeller.api")
    Call<BookList> getData(@Query("key") String key, @Query("categoryId") String categoryId, @Query("output") String output);
    @GET("/api/search.api")
    Call<BookList> getSearchData(@Query("key") String key, @Query("query") String query, @Query("queryType")String queryType, @Query("categoryId") String categoryId, @Query("output") String output);
}