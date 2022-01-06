package com.tsai.tsaimediaplayer.ext

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tsai.tsaimediaplayer.factory.ViewModelFactory
import com.tsai.tsaimediaplayer.ui.home.VideoInformation

fun Fragment.getVmFactory(): ViewModelFactory {
    return ViewModelFactory()
}

fun Fragment.getVmFactory(videoInformation: VideoInformation): ViewModelFactory {
    return ViewModelFactory(videoInformation)
}

fun Fragment.getVmFactory(videoInformation: Array<VideoInformation>): ViewModelFactory {
    return ViewModelFactory(videoInformationArray = videoInformation)
}

fun Fragment.toast(str: String) {
    Toast.makeText(requireContext(), str, Toast.LENGTH_SHORT).show()
}
