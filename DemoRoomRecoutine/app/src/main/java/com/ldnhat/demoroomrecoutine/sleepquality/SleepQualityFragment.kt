package com.ldnhat.demoroomrecoutine.sleepquality

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
import com.ldnhat.demoroomrecoutine.databinding.FragmentSleepQualityBinding

class SleepQualityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding:FragmentSleepQualityBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_quality, container, false)
        val arguments = SleepQualityFragmentArgs.fromBundle(requireArguments())
        val application = requireNotNull(this.activity).application
        val dataSource = SleepDatabase.getInstance(application).sleepNightDAO

        val viewModelFactory = SleepQualityViewModelFactory(arguments.sleepNightKey, dataSource)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SleepQualityViewModel::class.java)

        binding.lifecycleOwner = this
        binding.sleepQualityViewModel = viewModel

        viewModel.navigateToSleepTracker.observe(viewLifecycleOwner, {
            if (it == true){
                this.findNavController().navigate(SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment())
                viewModel.doneNavigation()
            }
        })
        return binding.root
    }
}