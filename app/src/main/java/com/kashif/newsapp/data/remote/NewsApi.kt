package com.kashif.newsapp.data.remote

import com.kashif.newsapp.data.remote.DtO.NewsResponse
import com.kashif.newsapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */
interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page:Int,
        @Query("sources") sources:String,
        @Query("apiKey") apiKey:String=Constants.API_KEY
    ):NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery:String,
        @Query("page") page:Int,
        @Query("sources") sources:String,
        @Query("apiKey") apiKey:String=Constants.API_KEY
    ):NewsResponse
}