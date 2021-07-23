package com.ldnhat.demoroomrecoutine.sleepquality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ldnhat.demoroomrecoutine.database.SleepNightDAO
import kotlinx.coroutines.*

class SleepQualityViewModel(private val sleepNightKey : Long = 0L,
        val database : SleepNightDAO) : ViewModel() {

            private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToSleepTracker = MutableLiveData<Boolean>()
    val navigateToSleepTracker:LiveData<Boolean>
        get() {
            return _navigateToSleepTracker
        }

    fun doneNavigation(){
        _navigateToSleepTracker.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onSetSleepQuality(quality : Int){
        uiScope.launch {
            withContext(Dispatchers.IO){
                val tonight = database.findById(sleepNightKey)?:return@withContext
                tonight.sleepQuality = quality
                database.update(tonight)
            }
            _navigateToSleepTracker.value = true
        }
    }

}