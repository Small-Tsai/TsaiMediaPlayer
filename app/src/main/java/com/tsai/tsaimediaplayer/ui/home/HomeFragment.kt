package com.tsai.tsaimediaplayer.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tsai.tsaimediaplayer.NavigationDirections
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
            adapter.submitList(it)
        })

        viewModel.isNavToVideoPage.observe(viewLifecycleOwner, {
            it?.let {
                findNavController().navigate(NavigationDirections.navToVideoPage(it))
            }
        })

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            homeRev.adapter = adapter
            return root
        }
    }
}