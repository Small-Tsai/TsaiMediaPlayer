package com.tsai.tsaimediaplayer.ui.videoInfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tsai.tsaimediaplayer.databinding.ItemVideoInfoBinding
import com.tsai.tsaimediaplayer.ui.home.VideoInformation

class VideoInfoAdapter(val videoViewModel: VideoInfoViewModel) :
    ListAdapter<VideoInformation, RecyclerView.ViewHolder>(DiffCallBack) {

    companion object DiffCallBack : DiffUtil.ItemCallback<VideoInformation>() {
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

    inner class OtherVideoViewHolder(private val binding: ItemVideoInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(videoInfo: VideoInformation) {
            binding.videoInfo = videoInfo

            binding.viewModel = videoViewModel

            if (absoluteAdapterPosition == currentList.size - 1) {
                binding.videoSeparation.visibility = View.INVISIBLE
            } else {
                binding.videoSeparation.visibility = View.VISIBLE
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OtherVideoViewHolder(
            ItemVideoInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is OtherVideoViewHolder -> holder.bind(getItem(position))
        }
    }
}