package com.daniluk.youtubemanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daniluk.youtubemanager.R
import com.daniluk.youtubemanager.data.YoutubeVideo
import kotlinx.android.synthetic.main.item_list_video.view.*

class AdapterSearchVideo: RecyclerView.Adapter<AdapterSearchVideo.VideoHolder>() {
    var listVideo = listOf<YoutubeVideo>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_video, parent, false)
        return VideoHolder(view)
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.title.text = listVideo[position].title
        holder.description.text = listVideo[position].description
        holder.checkBox.isChecked = listVideo[position].checkBox
    }

    override fun getItemCount() = listVideo.size

    inner class VideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val description = itemView.tvItemYoutubeDescription
        val title = itemView.tvItemYoutubeTitle
        val image = itemView.ivYoutube
        val checkBox = itemView.cbYoutube

        init {
            checkBox.setOnClickListener {
                listVideo[position].checkBox = checkBox.isChecked
            }
        }
    }

}