package com.daniluk.youtubemanager.pojo


import android.graphics.pdf.PdfDocument.PageInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class FullResponse(
    @SerializedName("items")
    @Expose
    val items: List<Item>? = null,

    @SerializedName("kind")
@Expose
val kind: String? = null,

@SerializedName("etag")
@Expose
val etag: String? = null,

@SerializedName("nextPageToken")
@Expose
val nextPageToken: String? = null,

@SerializedName("regionCode")
@Expose
val regionCode: String? = null,

@SerializedName("pageInfo")
@Expose
val pageInfo: PageInfo? = null


)