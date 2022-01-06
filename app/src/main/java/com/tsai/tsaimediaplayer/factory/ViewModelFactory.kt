package com.tsai.tsaimediaplayer.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tsai.tsaimediaplayer.ui.MainViewModel
import com.tsai.tsaimediaplayer.ui.home.HomeViewModel
import com.tsai.tsaimediaplayer.ui.home.VideoInformation
import com.tsai.tsaimediaplayer.ui.video.VideoViewModel
import com.tsai.tsaimediaplayer.ui.videoInfo.VideoInfoViewModel

class ViewModelFactory(
    private val videoInformation: VideoInformation? = null,
    private val videoInformationArray: Array<VideoInformation>? = null,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel()

                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel()

                isAssignableFrom(VideoViewModel::class.java) ->
                    VideoViewModel(videoInformationArray)

                isAssignableFrom(VideoInfoViewModel::class.java) ->
                    videoInformation?.let { VideoInfoViewModel(it) }

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}