package com.ldnhat.demoroomrecoutine.sleepquality

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ldnhat.demoroomrecoutine.database.SleepDatabase
import com.ldnhat.demoroomrecoutine.database.SleepNightDAO

class SleepQualityViewModelFactory(private val sleepNightKey : Long,
    private val dataSource:SleepNightDAO) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepQualityViewModel::class.java)){
            return SleepQualityViewModel(sleepNightKey, dataSource) as T
        }
        throw IllegalArgumentException("unknown viewModel SleepQualityViewModel")
    }
}