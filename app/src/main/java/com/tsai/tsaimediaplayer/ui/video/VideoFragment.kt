package com.tsai.tsaimediaplayer.ui.video

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.ui.PlayerView.SHOW_BUFFERING_ALWAYS
import com.google.android.exoplayer2.ui.PlayerView.VISIBLE
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.tsai.tsaimediaplayer.R
import com.tsai.tsaimediaplayer.databinding.FragmentVideoBinding
import com.tsai.tsaimediaplayer.ext.getVmFactory
import com.tsai.tsaimediaplayer.ext.toast
import com.tsai.tsaimediaplayer.ui.MainViewModel


class VideoFragment : Fragment() {

    private lateinit var binding: FragmentVideoBinding
    private lateinit var exoPlayer: ExoPlayer
    private lateinit var styledPlayer: StyledPlayerView

    private val viewModel by viewModels<VideoViewModel> {
        getVmFactory()
    }

    private lateinit var mainViewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        mainViewModel.currentVideoType.value?.let { viewModel.getVideoList(it) }

        // set backPressed behavior
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            callback
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentVideoBinding.inflate(inflater, container, false)

        initPlayer()

        setupPlayer()

        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        viewModel.mediaItem.observe(viewLifecycleOwner, {
            exoPlayer.setMediaItems(it)
        })

        return binding.root
    }

    private fun setupPlayer() {
        exoPlayer.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                if (isPlaying) {
                    binding.progressBar.visibility = GONE
                }
            }

            override fun onPlayerError(error: PlaybackException) {
                super.onPlayerError(error)
                toast("$error")
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
                when (playbackState) {
                    Player.STATE_BUFFERING -> binding.progressBar.visibility = VISIBLE
                }
            }
        })

        styledPlayer.setControllerOnFullScreenModeChangedListener {
            if (it) {
                requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            } else {
                requireActivity().requestedOrientation =
                    ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
        }
    }

    private fun initPlayer() {
        styledPlayer = binding.exoPlayer
        exoPlayer = ExoPlayer.Builder(requireContext()).build()
        styledPlayer.player = exoPlayer
    }

    override fun onStart() {
        super.onStart()
        exoPlayer.prepare()
        exoPlayer.play()
    }

    override fun onStop() {
        super.onStop()
        exoPlayer.stop()
        exoPlayer.release()
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

}