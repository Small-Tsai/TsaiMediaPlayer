package com.tsai.tsaimediaplayer.ext

import androidx.fragment.app.Fragment
import com.tsai.tsaimediaplayer.factory.ViewModelFactory
import com.tsai.tsaimediaplayer.ui.home.VideoInformation

fun Fragment.getVmFactory(): ViewModelFactory {
    return ViewModelFactory()
}

fun Fragment.getVmFactory(videoInformation: VideoInformation): ViewModelFactory {
    return ViewModelFactory(videoInformation)
}