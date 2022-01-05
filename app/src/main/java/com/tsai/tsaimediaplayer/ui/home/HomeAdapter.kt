package com.tsai.tsaimediaplayer.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tsai.tsaimediaplayer.databinding.ItemHomeVideoRevBinding
import com.tsai.tsaimediaplayer.databinding.ItemHomeVideoTitleBinding

class HomeAdapter : ListAdapter<MyVideo, RecyclerView.ViewHolder>(DiffCallBack) {

    companion object DiffCallBack : DiffUtil.ItemCallback<MyVideo>() {
        override fun areItemsTheSame(oldItem: MyVideo, newItem: MyVideo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MyVideo, newItem: MyVideo): Boolean {
            return oldItem == newItem
        }

        private const val ITEM_VIEW_TYPE_TITLE = 0x00
        private const val ITEM_VIEW_TYPE_VIDEO = 0x01
    }

    inner class VideoViewHolder(private val binding: ItemHomeVideoRevBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(videoInfo: List<VideoInformation>, adapter: VideoImageAdapter) {
            adapter.submitList(videoInfo)
            binding.homeVideoRev.adapter = adapter
        }
    }

    inner class VideoTypeViewHolder(private val binding: ItemHomeVideoTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(type: String) {
            binding.videoType.text = type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_TITLE -> VideoTypeViewHolder(
                ItemHomeVideoTitleBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
            )

            ITEM_VIEW_TYPE_VIDEO -> VideoViewHolder(
                ItemHomeVideoRevBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw ClassCastException("Unknown viewType $viewType")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is VideoViewHolder -> holder.bind(
                (getItem(position) as MyVideo.VideoInfo).video,
                (getItem(position) as MyVideo.VideoInfo).adapter
            )
            is VideoTypeViewHolder -> holder.bind((getItem(position) as MyVideo.Type).type)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MyVideo.Type -> ITEM_VIEW_TYPE_TITLE
            is MyVideo.VideoInfo -> ITEM_VIEW_TYPE_VIDEO
        }
    }
}

sealed class MyVideo {
    data class Type(val type: String) : MyVideo()
    data class VideoInfo(val video: List<VideoInformation>, val adapter: VideoImageAdapter) :
        MyVideo()
}