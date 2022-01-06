package com.tsai.tsaimediaplayer.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tsai.tsaimediaplayer.util.CurrentFragmentType

class MainViewModel : ViewModel() {

    // Record current fragment to support data binding
    val currentFragmentType = MutableLiveData<CurrentFragmentType>()

    // Record current video type
    val currentVideoType = MutableLiveData<String>()
}