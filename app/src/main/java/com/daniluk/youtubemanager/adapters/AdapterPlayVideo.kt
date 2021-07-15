package com.daniluk.youtubemanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.daniluk.youtubemanager.R
import com.daniluk.youtubemanager.pojo.YoutubeVideo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_play_list.view.*

class AdapterPlayVideo : RecyclerView.Adapter<AdapterPlayVideo.VideoHolder>() {
    var listVideo = listOf<YoutubeVideo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var deleteVideo: DeleteVideo? = null
    interface DeleteVideo {
        fun delete(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_play_list, parent, false)
        return VideoHolder(view)
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.title.text = listVideo[position].title
        holder.description.text = listVideo[position].description
        Picasso.get().load(listVideo[position].imageUrl).into(holder.image)
    }

    override fun getItemCount() = listVideo.size

    inner class VideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val description = itemView.tvItemPlayDescription
        val title = itemView.tvItemPlayTitle
        val image = itemView.ivPlay
        private val btDelete = itemView.btDelete

        init {
            btDelete.setOnClickListener {
                deleteVideo?.delete(position = position)
            }

            itemView.clParent.setOnClickListener {
                Toast.makeText(itemView.context, listVideo.get(position).title, Toast.LENGTH_SHORT).show()
            }

        }
    }

}