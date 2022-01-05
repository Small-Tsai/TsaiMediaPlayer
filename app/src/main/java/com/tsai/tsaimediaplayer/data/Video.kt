package com.tsai.tsaimediaplayer.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    val id: Long,
    val videoUrl: String,
    val name: String,
    val content: String,
) : Parcelable
