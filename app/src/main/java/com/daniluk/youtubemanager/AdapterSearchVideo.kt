package com.daniluk.youtubemanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterSearchVideo: RecyclerView.Adapter<VideoHolder>() {
    var listVideo = arrayListOf<YoutubeVideo>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_video, parent, false)
        return VideoHolder(view)
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.title = listVideo.get(position).title
        holder.description = listVideo.get(position).description
    }

    override fun getItemCount() = listVideo.size
}