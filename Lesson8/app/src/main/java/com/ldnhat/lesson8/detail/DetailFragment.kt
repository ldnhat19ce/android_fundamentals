package com.ldnhat.lesson8.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ldnhat.lesson8.R
import com.ldnhat.lesson8.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        val marsProperty = DetailFragmentArgs.fromBundle(requireArguments()).selectedProperty

        val application = requireActivity().application
        val detailViewModelFactory = DetailViewModelFactory(application, marsProperty)
        val detailViewModel = ViewModelProvider(this, detailViewModelFactory).get(DetailViewModel::class.java)

        binding.viewModel = detailViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}