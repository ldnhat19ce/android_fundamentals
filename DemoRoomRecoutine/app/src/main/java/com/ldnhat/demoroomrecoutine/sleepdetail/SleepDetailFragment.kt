package com.ldnhat.demoroomrecoutine.sleepdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ldnhat.demoroomrecoutine.R
import com.ldnhat.demoroomrecoutine.database.SleepDatabase
import com.ldnhat.demoroomrecoutine.databinding.FragmentSleepDetailBinding

class SleepDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding:FragmentSleepDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_detail, container, false)
        val arguments =  SleepDetailFragmentArgs.fromBundle(requireArguments())
        val dataSource = SleepDatabase.getInstance(requireNotNull(activity).application).sleepNightDAO

        val viewModelFactory = SleepDetailViewModelFactory(arguments.sleepNightKey,dataSource)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SleepDetailViewModel::class.java)

        binding.sleepDetailViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigationToSleepTracker.observe(viewLifecycleOwner, {
            if (it == true){
                this.findNavController().navigate(SleepDetailFragmentDirections.actionSleepDetailFragmentToSleepTrackerFragment())
                viewModel.doneNavigating()
            }
        })
        return binding.root
    }
}