package com.daniluk.youtubemanager.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
   private val BASE_URL = "https://youtube.googleapis.com/youtube/v3/"
   private val retrofit = Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(BASE_URL)
      .build()

   val apiService = retrofit.create(ApiService::class.java)
}