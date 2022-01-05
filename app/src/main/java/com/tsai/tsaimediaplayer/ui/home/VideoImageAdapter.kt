package com.tsai.tsaimediaplayer.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tsai.tsaimediaplayer.databinding.ItemHomeVideoBinding

class VideoImageAdapter :
    ListAdapter<VideoInformation, VideoImageAdapter.FavoriteImageViewHolder>(DiffCallback) {

    inner class FavoriteImageViewHolder(
        private var binding: ItemHomeVideoBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(videoInfo: VideoInformation) {
            binding.videoInfo = videoInfo
            binding.executePendingBindings()
        }
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<VideoInformation>() {
        override fun areItemsTheSame(
            oldItem: VideoInformation,
            newItem: VideoInformation,
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: VideoInformation,
            newItem: VideoInformation,
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteImageViewHolder {
        return FavoriteImageViewHolder(
            ItemHomeVideoBinding
                .inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(holder: FavoriteImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}