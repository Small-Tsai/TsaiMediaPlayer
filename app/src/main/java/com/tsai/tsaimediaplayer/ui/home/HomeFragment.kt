package com.tsai.tsaimediaplayer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tsai.tsaimediaplayer.NavigationDirections
import com.tsai.tsaimediaplayer.databinding.FragmentHomeBinding
import com.tsai.tsaimediaplayer.ext.getVmFactory
import com.tsai.tsaimediaplayer.ui.MainViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel> { getVmFactory() }
    private lateinit var mainViewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Create adapter
        val adapter = HomeAdapter()

        // SubmitList for adapter
        viewModel.myVideoList.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        // Navigation to videoPage
        viewModel.isNavToVideoInfoPage.observe(viewLifecycleOwner, {
            it?.let {
                findNavController().navigate(NavigationDirections.navToVideoInfoPage(it))
            }
        })

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            homeRev.adapter = adapter
            return root
        }
    }
}