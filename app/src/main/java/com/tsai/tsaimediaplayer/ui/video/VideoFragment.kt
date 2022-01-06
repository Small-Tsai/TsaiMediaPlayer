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
        getVmFactory(
            VideoFragmentArgs.fromBundle(requireArguments()).videoList
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * custom backPressed behavior to make sure screen orientation is portrait in videoInfoPage
         */
        setBackPressedBehavior()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentVideoBinding.inflate(inflater, container, false)

        initPlayer()
        setupPlayer()

        /**
         * make sure always keep screen on when video is playing
         */
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        viewModel.mediaItem.observe(viewLifecycleOwner, {
            exoPlayer.setMediaItems(it)
        })

        return binding.root
    }

    private fun setupPlayer() {

        exoPlayer.addListener(object : Player.Listener {

            /**
             * when [exoPlayer] isPlaying hide progressBar
             */
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                if (isPlaying) {
                    binding.progressBar.visibility = GONE
                }
            }

            /**
             * when [exoPlayer] error show toast of error message
             */
            override fun onPlayerError(error: PlaybackException) {
                super.onPlayerError(error)
                toast("$error")
            }

            /**
             * when [exoPlayer] is buffering show progressBar
             */
            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
                when (playbackState) {
                    Player.STATE_BUFFERING -> binding.progressBar.visibility = VISIBLE
                }
            }
        })

        /**
         * custom [styledPlayer] full screen button behavior
         */
        styledPlayer.setControllerOnFullScreenModeChangedListener {
            if (it) {
                requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            } else {
                requireActivity().requestedOrientation =
                    ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
        }
    }

    /**
     * initialize [styledPlayer] and [exoPlayer]
     */
    private fun initPlayer() {
        styledPlayer = binding.exoPlayer
        exoPlayer = ExoPlayer.Builder(requireContext()).build()
        styledPlayer.player = exoPlayer
    }

    /**
     * when fragment's lifecycle onStart prepare and play [exoPlayer]
     */
    override fun onStart() {
        super.onStart()
        exoPlayer.prepare()
        exoPlayer.play()
    }

    /**
     * when fragment's lifecycle onStop stop and release [exoPlayer]
     */
    override fun onStop() {
        super.onStop()
        exoPlayer.stop()
        exoPlayer.release()
    }

    /**
     * when fragment's lifecycle onDestroy clear FLAG_KEEP_SCREEN_ON
     */
    override fun onDestroy() {
        super.onDestroy()
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private fun setBackPressedBehavior() {
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

}