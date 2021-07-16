package com.daniluk.youtubemanager

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.daniluk.youtubemanager.api.ApiFactory
import com.daniluk.youtubemanager.api.ApiService.Companion.createLog
import com.daniluk.youtubemanager.pojo.FullResponse
import com.daniluk.youtubemanager.pojo.YoutubeVideo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YoutubeViewModel(application: Application): AndroidViewModel(application) {
    val context = application.applicationContext
    val searchListVideo = MutableLiveData(mutableListOf<YoutubeVideo>()) //список найденных видео
    val playListVideo = MutableLiveData(mutableListOf<YoutubeVideo>())   //плейлист
    var typeFragment = MutableLiveData(true) //true - фрагмент поиска, false - фрагмент плейлиста

    init {
        //Проверка доступности интернета
        if (isNetworkAvaliable(application)) {
        searchVideo(application.getString(R.string.STRING_SEARCH_DEFAULT)) //поиск строки по умолчанию при старте приложения
        }else{
            Toast.makeText(application, application.getString(R.string.NO_INTERNET), Toast.LENGTH_LONG).show()
        }
    }

    //поиск видео по строке
    fun searchVideo(stringSearch: String){
        if (isNetworkAvaliable(context)) {
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
        }else{
            Toast.makeText(context, context.getString(R.string.NO_INTERNET), Toast.LENGTH_LONG).show()
        }

    }

    //Проверка доступности интернета
    private fun isNetworkAvaliable(context: Context) : Boolean {
        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return  if(Build.VERSION.SDK_INT > 22){
                val an = cm.activeNetwork ?: return false
                val capabilities = cm.getNetworkCapabilities(an) ?: return  false
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            }else {
                val a = cm.activeNetworkInfo ?: return false
                a.isConnected && (a.type == ConnectivityManager.TYPE_WIFI || a.type == ConnectivityManager.TYPE_MOBILE)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

}