package com.ldnhat.styleandtheme.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ldnhat.styleandtheme.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HomeFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.homeViewModel = viewModel

        viewModel.navigateToSearch.observe(viewLifecycleOwner, {
            if (it){
                this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToGdgListFragment())
                viewModel.onNavigatedToSearch()
            }
        })
        return binding.root
    }
}