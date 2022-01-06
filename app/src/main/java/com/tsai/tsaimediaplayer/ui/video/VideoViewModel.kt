package com.tsai.tsaimediaplayer.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.android.exoplayer2.MediaItem
import com.tsai.tsaimediaplayer.ui.home.VideoInformation

class VideoViewModel : ViewModel() {

    private val _mediaItem = MutableLiveData<List<MediaItem>>()
    val mediaItem: LiveData<List<MediaItem>>
        get() = _mediaItem

    fun getVideoList(type: String) {
        val videoUrlList = VideoInformation.values().filter { it.type == type }.map { it.url }
        val videoList = mutableListOf<MediaItem>()

        if (videoList.isEmpty()) {
            videoUrlList.forEach {
                videoList.add(MediaItem.fromUri(it))
            }

        }

        _mediaItem.value = videoList
    }

}