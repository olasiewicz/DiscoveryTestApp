package com.example.myapplication.ui.main.home

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.model.Stories
import com.example.myapplication.model.Videos
import com.example.myapplication.ui.main.MainViewModel
import com.example.myapplication.ui.main.state.MainStateEvent

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private var homeAdapter: HomeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        subscribeObservers()

        viewModel.setStateEvent(MainStateEvent.GetMedia)
    }

    private fun initRecyclerView() {
        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
            homeAdapter = HomeAdapter { param: Any -> onItemClick(param) }
            adapter = homeAdapter
        }
    }

    private fun onItemClick(param: Any) {

        when (param) {
            is Videos -> navigateToPlayerFragment(param.url)
            is Stories -> navigateToArticleFragment()
        }
    }

    private fun navigateToArticleFragment() {

    }

    private fun navigateToPlayerFragment(url: String) {
        val direction = HomeFragmentDirections.actionHomeFragmentToPlayerFragment()
            .setVideoURL(url)
        findNavController().navigate(direction)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->

            println("DEBUG: DataState: ${dataState}")

            // Handle Loading and Message
            // dataStateHandler.onDataStateChange(dataState)

            dataState.data?.let { mainViewState ->
                mainViewState.media?.let { it ->
                    viewModel.setMedia(it)

                }
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            viewState.media?.let {
                // set BlogPosts to RecyclerView
                println("DEBUG: Setting blog posts to RecyclerView: ${viewState.media}")


                homeAdapter?.setMedia(it)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}