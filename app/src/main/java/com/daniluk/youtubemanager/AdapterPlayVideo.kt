package com.daniluk.youtubemanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_video.view.*
import kotlinx.android.synthetic.main.item_play_list.view.*

class AdapterPlayVideo : RecyclerView.Adapter<AdapterPlayVideo.VideoHolder>() {
    var listVideo = listOf<YoutubeVideo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    val deleteVideo: DeleteVideo? = null
    interface DeleteVideo {
        fun delete(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_play_list, parent, false)
        return VideoHolder(view)
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.title.text = listVideo.get(position).title
        holder.description.text = listVideo.get(position).description
    }

    override fun getItemCount() = listVideo.size

    inner class VideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val description = itemView.tvItemPlayDescription
        val title = itemView.tvItemPlayTitle
        val image = itemView.ivPlay
        val btDelete = itemView.btDelete

        init {
            btDelete.setOnClickListener {
                deleteVideo?.delete(position = position)
            }
        }
    }

}