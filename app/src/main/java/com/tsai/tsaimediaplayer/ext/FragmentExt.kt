package com.tsai.tsaimediaplayer.ext

import androidx.fragment.app.Fragment
import com.tsai.tsaimediaplayer.factory.ViewModelFactory

fun Fragment.getVmFactory(): ViewModelFactory {
    return ViewModelFactory()
}