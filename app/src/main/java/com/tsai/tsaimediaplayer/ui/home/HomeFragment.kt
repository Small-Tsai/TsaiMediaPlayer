package com.tsai.tsaimediaplayer.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.tsai.tsaimediaplayer.R
import com.tsai.tsaimediaplayer.data.Video
import com.tsai.tsaimediaplayer.databinding.FragmentHomeBinding
import com.tsai.tsaimediaplayer.ext.getVmFactory
import com.tsai.tsaimediaplayer.util.Logger

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val adapter = HomeAdapter()
        viewModel.myVideoList.observe(viewLifecycleOwner, {
            Logger.d("$it")
            adapter.submitList(it)
        })

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            homeRev.adapter = adapter
            return root
        }
    }
}