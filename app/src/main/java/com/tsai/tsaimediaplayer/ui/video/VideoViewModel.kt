package com.tsai.tsaimediaplayer.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.android.exoplayer2.MediaItem
import com.tsai.tsaimediaplayer.ui.home.VideoInformation

class VideoViewModel(val videoInformationArray: Array<VideoInformation>?) : ViewModel() {

    /**
     * LiveData for list of same type video which MediaItem is get from videoUrl
     */
    val mediaItem: LiveData<List<MediaItem>> = liveData {

        val videoList = mutableListOf<MediaItem>()

        videoInformationArray?.let { videoInformationArray ->
            videoInformationArray.map { it.url }.forEach {
                videoList.add(MediaItem.fromUri(it))
            }
        }

        emit(videoList)
    }
}