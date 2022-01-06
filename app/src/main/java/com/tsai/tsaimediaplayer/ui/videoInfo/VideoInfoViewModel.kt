package com.tsai.tsaimediaplayer.ui.videoInfo

import androidx.lifecycle.*
import com.tsai.tsaimediaplayer.ui.home.VideoInformation
import com.tsai.tsaimediaplayer.util.Logger

class VideoInfoViewModel(val videoInformation: VideoInformation) : ViewModel() {

    private val _isNavToVideoPage = MutableLiveData<List<VideoInformation>?>()
    val isNavToVideoPage: LiveData<List<VideoInformation>?>
        get() = _isNavToVideoPage

    private val sameTypeVideoList: LiveData<List<VideoInformation>> = liveData {
        emit(
            VideoInformation.values()
                .filter { it.type == videoInformation.type }
        )
    }

    val otherSameTypeVideoList: LiveData<List<VideoInformation>> =
        Transformations.map(sameTypeVideoList) { sameTypeVideoList ->
            sameTypeVideoList.filter { it.id != videoInformation.id }
        }

    fun playVideo() {
        _isNavToVideoPage.value = sameTypeVideoList.value
        _isNavToVideoPage.value = null
    }

    fun playSameTypeVideo(episode: Int) {
        Logger.d("$episode")
        val list = sameTypeVideoList.value?.filter { it.episode >= episode }

        _isNavToVideoPage.value = list

        _isNavToVideoPage.value = null
    }

}