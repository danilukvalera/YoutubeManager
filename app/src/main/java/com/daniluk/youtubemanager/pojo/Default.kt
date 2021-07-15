package com.daniluk.youtubemanager.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class Default(
    @SerializedName("url")
    @Expose
    val url: String? = null,

    @SerializedName("width")
    @Expose
    val width: Int? = null,

    @SerializedName("height")
    @Expose
    val height: Int? = null

)
