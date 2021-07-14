package com.daniluk.youtubemanager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daniluk.youtubemanager.data.YoutubeVideo

class YoutubeViewModel: ViewModel() {
    val searchListVideo = MutableLiveData(mutableListOf<YoutubeVideo>())
    val playListVideo = MutableLiveData(mutableListOf<YoutubeVideo>())


}