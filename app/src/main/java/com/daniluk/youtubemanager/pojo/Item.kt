package com.daniluk.youtubemanager.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class Item(
    @SerializedName("snippet")
    @Expose
    val snippet: Snippet? = null
)
