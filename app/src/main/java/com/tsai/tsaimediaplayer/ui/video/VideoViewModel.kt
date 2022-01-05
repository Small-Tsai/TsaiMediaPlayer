package com.tsai.tsaimediaplayer.ui.video

import androidx.lifecycle.ViewModel
import com.tsai.tsaimediaplayer.ui.home.VideoInformation
import com.tsai.tsaimediaplayer.util.Logger

class VideoViewModel(val videoInformation: VideoInformation) : ViewModel() {

    init {
        Logger.d("info = ${videoInformation.videoName}")
    }

}