package com.tsai.tsaimediaplayer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.tsai.tsaimediaplayer.R
import com.tsai.tsaimediaplayer.databinding.ActivityMainBinding
import com.tsai.tsaimediaplayer.ext.getVmFactory
import com.tsai.tsaimediaplayer.util.CurrentFragmentType
import com.tsai.tsaimediaplayer.util.Logger

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel> { getVmFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.currentFragmentType.observe(this, {
            Logger.d("current fragment type = $it")
        })

        setupNavController()
    }

    private fun setupNavController() {
        findNavController(R.id.nav_host_fragment_activity_main)
            .addOnDestinationChangedListener { navController: NavController, _, _ ->
                viewModel.currentFragmentType.value = when (navController.currentDestination?.id) {
                    R.id.homeFragment -> CurrentFragmentType.Home
                    R.id.videoInfoFragment -> CurrentFragmentType.VIDEO
                    else -> viewModel.currentFragmentType.value
                }
            }
    }
}