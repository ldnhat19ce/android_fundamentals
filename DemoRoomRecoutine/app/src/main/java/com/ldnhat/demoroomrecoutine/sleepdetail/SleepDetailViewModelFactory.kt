package com.ldnhat.demoroomrecoutine.sleepdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ldnhat.demoroomrecoutine.database.SleepNightDAO

class SleepDetailViewModelFactory(private val sleepNightKey:Long, private val dataSource : SleepNightDAO) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepDetailViewModel::class.java)){
            return SleepDetailViewModel(sleepNightKey, dataSource) as T
        }
        throw IllegalArgumentException()
    }
}