package com.tsai.tsaimediaplayer

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.tsai.tsaimediaplayer.util.Logger

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    if (!imgUrl.isNullOrEmpty()) {
        Logger.d(imgUrl)
        Glide.with(imgView.context)
            .load(imgUrl)
            .centerCrop()
            .placeholder(R.drawable.video_placeholder)
            .into(imgView)
    }
}