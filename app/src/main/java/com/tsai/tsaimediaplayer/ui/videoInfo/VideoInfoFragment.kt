package com.tsai.tsaimediaplayer.ui.videoInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tsai.tsaimediaplayer.NavigationDirections
import com.tsai.tsaimediaplayer.databinding.FragmentVideoInfoBinding
import com.tsai.tsaimediaplayer.ext.getVmFactory

class VideoInfoFragment : Fragment() {

    private val videoViewModel by viewModels<VideoInfoViewModel> {
        getVmFactory(VideoInfoFragmentArgs.fromBundle(requireArguments()).video)
    }

    private lateinit var binding: FragmentVideoInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentVideoInfoBinding.inflate(inflater, container, false)

        val adapter = VideoInfoAdapter(videoViewModel)

        videoViewModel.isNavToVideoPage.observe(viewLifecycleOwner, {
            it?.let {
                findNavController()
                    .navigate(NavigationDirections.navToVideoPage(it.toTypedArray()))
            }
        })

        videoViewModel.otherSameTypeVideoList.observe(viewLifecycleOwner, {

            adapter.submitList(it)

            if (it.isEmpty()) binding.sameTypeVideoTxt.visibility = View.GONE
        })

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = videoViewModel

            playVideoBtn.setOnClickListener {
                videoViewModel.playVideo()
            }

            "第${videoViewModel.videoInformation.episode}集".also { episodeTxt.text = it }

            videoInfoRev.adapter = adapter

            return root
        }
    }
}