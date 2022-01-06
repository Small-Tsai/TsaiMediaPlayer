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

        videoViewModel.isNavToVideoPage.observe(viewLifecycleOwner, {
            it?.let {
                findNavController()
                    .navigate(NavigationDirections.navToVideoPage())
            }
        })

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = videoViewModel

            playVideoBtn.setOnClickListener {
                videoViewModel.navToVideoPage()
            }

            return root
        }
    }
}