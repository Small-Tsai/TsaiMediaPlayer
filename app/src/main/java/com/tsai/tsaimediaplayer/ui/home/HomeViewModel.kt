package com.tsai.tsaimediaplayer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _myVideoList =
        MutableLiveData<MutableList<MyVideo>>().apply { value = mutableListOf() }
    val myVideoList: LiveData<MutableList<MyVideo>>
        get() = _myVideoList

    /**
     * LiveData for navigation from home to videoPage
     */
    private val _isNavToVideoInfoPage = MutableLiveData<VideoInformation?>()
    val isNavToVideoInfoPage: LiveData<VideoInformation?>
        get() = _isNavToVideoInfoPage

    init {

        /**
         * get all the video type in [VideoInformation]
         */
        val titleList = VideoInformation.values().map { it.type }.distinct()

        /**
         * setup videoList and create VideoImageAdapter for horizontal recyclerView which
         * inside of the vertical recyclerView
         */
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

    /**
     * using DataBinding to get videoInfo from recyclerView Item when user click on video Image
     */
    fun navToVideoPage(videoInfo: VideoInformation) {
        _isNavToVideoInfoPage.value = videoInfo
        _isNavToVideoInfoPage.value = null
    }

}