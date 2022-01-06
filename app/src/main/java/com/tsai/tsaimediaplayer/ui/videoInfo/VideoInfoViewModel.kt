package com.tsai.tsaimediaplayer.ui.videoInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tsai.tsaimediaplayer.ui.home.VideoInformation
import com.tsai.tsaimediaplayer.util.Logger

class VideoInfoViewModel(val videoInformation: VideoInformation) : ViewModel() {

    private val _isNavToVideoPage = MutableLiveData<Boolean?>()
    val isNavToVideoPage: LiveData<Boolean?>
        get() = _isNavToVideoPage

    fun navToVideoPage(){
        _isNavToVideoPage.value = true
        _isNavToVideoPage.value = null
    }

}