package com.daniluk.youtubemanager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daniluk.youtubemanager.api.ApiFactory
import com.daniluk.youtubemanager.api.ApiService.Companion.createLog
import com.daniluk.youtubemanager.pojo.FullResponse
import com.daniluk.youtubemanager.pojo.YoutubeVideo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class YoutubeViewModel: ViewModel() {
    val searchListVideo = MutableLiveData(mutableListOf<YoutubeVideo>()) //список найденных видео
    val playListVideo = MutableLiveData(mutableListOf<YoutubeVideo>())   //плейлист
    var typeFragment = MutableLiveData(true) //true - фрагмент поиска, false - фрагмент плейлиста

    init {
        searchVideo("Tom and Jerry")
    }

    fun searchVideo(stringSearch: String){
        ApiFactory.apiService.searchVideo(stringSearch).enqueue(object: Callback<FullResponse>{
            override fun onResponse(call: Call<FullResponse>, response: Response<FullResponse>) {
                createLog("Поиск видео выполнен успешно")
                val listVideo = mutableListOf<YoutubeVideo>()
                val listItems = response.body()?.items
                if (listItems != null) {
                    for (item in listItems){
                        val title = item.snippet?.title ?: ""
                        val description = item.snippet?.description ?: ""
                        val imageUrl = item.snippet?.thumbnails?.default?.url ?: ""
                        listVideo.add(YoutubeVideo(title, description, imageUrl))
                    }
                    searchListVideo.postValue(listVideo)
                }
            }

            override fun onFailure(call: Call<FullResponse>, t: Throwable) {
                createLog("Ошибка поиска видео")
            }
        })
    }
}