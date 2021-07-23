package com.ldnhat.demoroomrecoutine.sleeptracker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ldnhat.demoroomrecoutine.database.SleepNightDAO
import java.lang.IllegalArgumentException

class SleepTrackerViewModelFactory (private val databaseSource : SleepNightDAO,
                private val application: Application
                                    ) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepTrackerViewModel::class.java)) {
            return SleepTrackerViewModel(databaseSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}