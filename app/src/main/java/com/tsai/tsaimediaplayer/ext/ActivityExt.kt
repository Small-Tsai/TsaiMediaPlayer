package com.tsai.tsaimediaplayer.ext

import android.app.Activity
import com.tsai.tsaimediaplayer.factory.ViewModelFactory

fun Activity.getVmFactory(): ViewModelFactory {
    return ViewModelFactory()
}