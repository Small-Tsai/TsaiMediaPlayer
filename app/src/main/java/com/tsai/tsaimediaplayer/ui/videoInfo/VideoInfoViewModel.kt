package com.tsai.tsaimediaplayer.ui.videoInfo

import androidx.lifecycle.*
import com.tsai.tsaimediaplayer.ui.home.VideoInformation
import com.tsai.tsaimediaplayer.util.Logger

class VideoInfoViewModel(val videoInformation: VideoInformation) : ViewModel() {

    private val _isNavToVideoPage = MutableLiveData<List<VideoInformation>?>()
    val isNavToVideoPage: LiveData<List<VideoInformation>?>
        get() = _isNavToVideoPage

    private val _isNavToVideoInfoPage = MutableLiveData<VideoInformation?>()
    val isNavToVideoInfoPage: LiveData<VideoInformation?>
        get() = _isNavToVideoInfoPage

    /**
     * LiveData for videoList which video type = user selected video type
     */
    private val sameTypeVideoList: LiveData<List<VideoInformation>> = liveData {
        emit(
            VideoInformation.values()
                .filter { it.type == videoInformation.type }
        )
    }

    /**
     * Transformation [sameTypeVideoList] to get videoList which not contains current selected video
     */
    val otherSameTypeVideoList: LiveData<List<VideoInformation>> =
        Transformations.map(sameTypeVideoList) { sameTypeVideoList ->
            sameTypeVideoList.filter { it.id != videoInformation.id }
        }

    /**
     * navigation to video page to play videos
     */
    fun playVideo() {
        _isNavToVideoPage.value =
            sameTypeVideoList.value?.filter { it.episode >= videoInformation.episode }

        _isNavToVideoPage.value = null
    }

    /**
     * when user click on same type video img play videos which episode >= user clicked video episode
     */
    fun playSameTypeVideo(episode: Int) {
        val list = sameTypeVideoList.value?.filter { it.episode >= episode }
        _isNavToVideoPage.value = list
        _isNavToVideoPage.value = null
    }

    /**
     * when user click on same type video layout navigation to videoInfo page of video which user click
     */
    fun navToVideoPage(videoInfo: VideoInformation) {
        _isNavToVideoInfoPage.value = videoInfo
        _isNavToVideoInfoPage.value = null
    }

}