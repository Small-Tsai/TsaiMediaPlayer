package com.tsai.tsaimediaplayer.ui.video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tsai.tsaimediaplayer.databinding.VideoFragmentBinding
import com.tsai.tsaimediaplayer.ext.getVmFactory

class VideoFragment : Fragment() {

    private val videoViewModel by viewModels<VideoViewModel> {
        getVmFactory(VideoFragmentArgs.fromBundle(requireArguments()).video)
    }

    private lateinit var binding: VideoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = VideoFragmentBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = videoViewModel
            return root
        }
    }
}