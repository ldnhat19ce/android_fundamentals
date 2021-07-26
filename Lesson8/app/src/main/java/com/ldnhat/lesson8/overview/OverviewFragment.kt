package com.ldnhat.lesson8.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ldnhat.lesson8.R
import com.ldnhat.lesson8.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding:FragmentOverviewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_overview, container, false)

        val overviewViewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)

        binding.overviewViewModel = overviewViewModel
        binding.photoGrid.adapter = PhotoGridAdapter()
        binding.lifecycleOwner = this
        return binding.root
    }
}   