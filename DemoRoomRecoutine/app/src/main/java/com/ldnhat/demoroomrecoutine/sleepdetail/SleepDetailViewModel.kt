package com.ldnhat.demoroomrecoutine.sleepdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ldnhat.demoroomrecoutine.database.SleepNight
import com.ldnhat.demoroomrecoutine.database.SleepNightDAO
import kotlinx.coroutines.*

class SleepDetailViewModel(private val sleepNightKey:Long, private val dataSource:SleepNightDAO) : ViewModel() {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _night = MutableLiveData<SleepNight>()

    val night:LiveData<SleepNight>
    get() = _night

    private val _navigationToSleepTracker = MutableLiveData<Boolean>()

    val navigationToSleepTracker : LiveData<Boolean>
    get() = _navigationToSleepTracker

    init {
        uiScope.launch {
            _night.value = findSleepNightById()
        }
    }

    private suspend fun findSleepNightById() : SleepNight?{
        return withContext(Dispatchers.IO){
            dataSource.findById(sleepNightKey)
        }
    }

    fun doneNavigating(){
        _navigationToSleepTracker.value = null
    }

    fun onClose(){
        _navigationToSleepTracker.value = true
    }
}