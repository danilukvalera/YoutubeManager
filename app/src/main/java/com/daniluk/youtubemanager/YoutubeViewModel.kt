package com.daniluk.youtubemanager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class YoutubeViewModel: ViewModel() {
    val searchListVideo = MutableLiveData(mutableListOf<YoutubeVideo>())
    val playListVideo = MutableLiveData(mutableListOf<YoutubeVideo>())


}