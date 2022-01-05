package com.tsai.tsaimediaplayer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tsai.tsaimediaplayer.util.Logger
import kotlinx.coroutines.flow.MutableSharedFlow

class HomeViewModel : ViewModel() {

    private val _myVideoList =
        MutableLiveData<MutableList<MyVideo>>().apply { value = mutableListOf() }
    val myVideoList: LiveData<MutableList<MyVideo>>
        get() = _myVideoList

    private val _isNavToVideoPage = MutableLiveData<VideoInformation?>()
    val isNavToVideoPage: LiveData<VideoInformation?>
        get() = _isNavToVideoPage

    init {

        val titleList = VideoInformation.values().map { it.type }.distinct()

        titleList.forEach { title ->
            _myVideoList.value?.let {
                it.add(
                    MyVideo.Type(title)
                )
                it.add(
                    MyVideo.VideoInfo(
                        VideoInformation.values().filter { it.type == title },
                        VideoImageAdapter(this)
                    )
                )
            }
        }
    }

    fun navToVideoPage(videoInfo: VideoInformation) {
        _isNavToVideoPage.value = videoInfo
        _isNavToVideoPage.value = null
    }

}