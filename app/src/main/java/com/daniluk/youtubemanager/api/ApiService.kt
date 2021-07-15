package com.daniluk.youtubemanager.api

import android.util.Log
import com.daniluk.youtubemanager.pojo.FullResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {
    //Поиск видео
    //@GET("https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=25&q=surfing&key=AIzaSyDtzsgyNZ3VpVGnEsJy-9Kf3Bm6g_F6z_8")
    @GET("search")
    fun searchVideo(
        @Query(QUERY_PARAM_STRING_SEARCH) stringSearch: String,
        @Query(QUERY_PARAM_MAX_RESULTS) maxResults: Int = PARAM_MAX_RESULTS,
        @Query(QUERY_PARAM_PART) part: String = PARAM_PATR_SNIPPET,
        @Query(QUERY_PARAM_KEY) apiKey: String = PARAM_API_KEY
    ): Call<FullResponse>

    companion object {
        //https://developers.google.com/youtube/v3/docs/activities/list?apix_params=%7B%22part%22%3A%5B%22snippet%2CcontentDetails%22%5D%2C%22channelId%22%3A%22UC_x5XG1OV2P6uZZ5FSM9Ttw%22%2C%22maxResults%22%3A25%7D
        //опции поиска
        const val QUERY_PARAM_PART = "part"                                     //String
        const val QUERY_PARAM_MAX_RESULTS = "maxResults"                        //Int
        const val QUERY_PARAM_KEY = "key"                                       //String
        const val QUERY_PARAM_STRING_SEARCH = "q"                               //String

        const val PARAM_API_KEY = "AIzaSyDtzsgyNZ3VpVGnEsJy-9Kf3Bm6g_F6z_8"     //String
        const val PARAM_PATR_SNIPPET = "snippet"                                //String
        const val PARAM_MAX_RESULTS = 25                                        //Int

        const val LOG_YOUTUBE = "yuotube"
        fun createLog(message: String) {
            Log.d(LOG_YOUTUBE, message)
        }
    }

}