package com.example.myapplication.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
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
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.homeButton.setOnClickListener(View.OnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_articleFragment2)
//        })
        initRecyclerView()
        subscribeObservers()

        viewModel.setStateEvent(MainStateEvent.GetMedia)
    }

    private fun initRecyclerView() {
        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
            homeAdapter = HomeAdapter()
            adapter = homeAdapter
        }
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->

            println("DEBUG: DataState: ${dataState}")

            // Handle Loading and Message
            // dataStateHandler.onDataStateChange(dataState)

            // handle Data<T>
            dataState.data?.let { mainViewState ->
                mainViewState.media?.let {
                    // set BlogPosts data
                    //  viewModel.setBlogListData(it)
                    Toast.makeText(
                        context?.applicationContext,
                        it.listOfVideos.get(0).title,
                        Toast.LENGTH_SHORT
                    ).show()

                    homeAdapter?.setMedia(it.listOfVideos)

                }

            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            viewState.media?.let {
                // set BlogPosts to RecyclerView
                println("DEBUG: Setting blog posts to RecyclerView: ${viewState.media}")
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}