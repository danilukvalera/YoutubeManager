package com.daniluk.youtubemanager.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class Thumbnails(
    @SerializedName("default")
    @Expose
    val default: Default? = null

)
