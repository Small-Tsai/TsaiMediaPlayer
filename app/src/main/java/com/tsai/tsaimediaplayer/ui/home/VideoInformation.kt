package com.tsai.tsaimediaplayer.ui.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class VideoInformation(
    val id: Long,
    val videoName: String,
    val url: String,
    val content: String,
    val img: String,
    val type: String,
    val episode: Int,
) : Parcelable {
    VIDEO_0(0,
        "1.Android 10s",
        "https://storage.googleapis.com/exoplayer-test-media-1/mp4/android-screens-10s.mp4",
        "床上的男人與狗床上的男人與狗床上的男人與狗床上的男人與狗床上的男人與狗床上的男人與狗床上的男人與狗床上的男人與狗床上的男人與狗床上的男人與狗床上的男人與狗床上的男人與狗床上的男人與狗床上的男人與狗床上的男人與狗床上的男人與狗床上的男人與狗床上的男人與狗床上的男人與狗",
        "https://pic.52112.com/180425/180425_189/N4rAC6MEcA_small.jpg",
        "Android",
        1
    ),

    VIDEO_1(1,
        "2.Android 25s",
        "https://storage.googleapis.com/exoplayer-test-media-1/mp4/android-screens-25s.mp4",
        "床上的男人與狗2",
        "https://pic.52112.com/180425/180425_189/N4rAC6MEcA_small.jpg",
        "Android",
        2
    ),

    VIDEO_2(2,
        "Frame Counter",
        "https://storage.googleapis.com/exoplayer-test-media-1/mp4/frame-counter-one-hour.mp4",
        "藍底白字",
        "https://thecounter.org/wp-content/uploads/2020/01/OneLiner_Image_Blue.jpg",
        "Frame Counter",
        1
    ),

    VIDEO_3(3,
        "1.Dizzy",
        "https://html5demos.com/assets/dizzy.mp4",
        "貓咪原地自轉",
        "https://ak.picdn.net/shutterstock/videos/1033213943/thumb/11.jpg",
        "Cat",
        1
    ),

    VIDEO_4(4,
        "3.Android No Sound",
        "https://storage.googleapis.com/exoplayer-test-media-1/gen-3/screens/dash-vod-single-segment/video-avc-baseline-480.mp4\n",
        "床上的男人與狗 禁音版",
        "https://pic.52112.com/180425/180425_189/N4rAC6MEcA_small.jpg",
        "Android",
        3
    ),

    VIDEO_5(5,
        "2.Dizzy 2",
        "https://storage.googleapis.com/exoplayer-test-media-1/mp4/dizzy-with-tx3g.mp4\n",
        "貓咪原地自轉2",
        "https://ak.picdn.net/shutterstock/videos/1033213943/thumb/11.jpg",
        "Cat",
        2
    ),

    VIDEO_6(6,
        "4.Android No Sound 2",
        "https://storage.googleapis.com/exoplayer-test-media-1/gen-3/screens/dash-vod-single-segment/video-137.mp4\n",
        "床上的男人與狗 禁音版2",
        "https://pic.52112.com/180425/180425_189/N4rAC6MEcA_small.jpg",
        "Android",
        4
    ),

    VIDEO_7(7,
        "Big Buck Bunny",
        "https://storage.googleapis.com/downloads.webmproject.org/av1/exoplayer/bbb-av1-480p.mp4\n",
        "被邪惡動物們霸凌的中年肥兔子精心計劃著一場復仇，邪惡動物們即將面臨想也想不到的可怕夢魘...",
        "https://ti-wb.github.io/creativecommon-tw/sites/creativecommons.tw/files/bbb-splash.png",
        "Big Buck Bunny",
        1
    )
}