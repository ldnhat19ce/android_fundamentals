package com.ldnhat.demoroomrecoutine.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ldnhat.demoroomrecoutine.database.SleepNight
import com.ldnhat.demoroomrecoutine.database.SleepNightDAO
import com.ldnhat.demoroomrecoutine.formatNights
import kotlinx.coroutines.*

class SleepTrackerViewModel(val database : SleepNightDAO, application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var tonight = MutableLiveData<SleepNight>()

    val nights = database.findAll()

    init {
        initializeTonight()
    }

    val startButtonVisible = Transformations.map(tonight){
        it == null
    }

    val stopButtonVisible = Transformations.map(tonight){
        it != null
    }

    val clearButtonVisible = Transformations.map(nights){
        it.isNotEmpty()
    }

    private fun initializeTonight(){
        uiScope.launch {
            tonight.value = getTonightFromDatabase()
        }
    }

    private val _navigateToSleepQuality = MutableLiveData<SleepNight>()
    val navigateToSleepQuality:LiveData<SleepNight>
        get() {
          return _navigateToSleepQuality
        }

    fun doneNavigation(){
        _navigateToSleepQuality.value = null
    }

    private suspend fun getTonightFromDatabase(): SleepNight? {
        return withContext(Dispatchers.IO){
            var night = database.findTonight()
            if (night?.endTimeMilli != night?.startTimeMilli){
                night = null
            }
            night
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onStartTracking(){
        uiScope.launch {
            val newNight = SleepNight()
            insert(newNight)
            tonight.value = getTonightFromDatabase()
        }
    }

    private suspend fun insert(night: SleepNight){
        withContext(Dispatchers.IO){
            database.insert(night)
        }
    }

    val nightsString = Transformations.map(nights){
        nights -> formatNights(nights, application.resources)
    }

    fun onStopTracking(){
        uiScope.launch {
            val oldNight = tonight.value?:return@launch
            oldNight.endTimeMilli = System.currentTimeMillis()
            update(oldNight)
            _navigateToSleepQuality.value = oldNight
        }
    }

    private suspend fun update(night: SleepNight){
        withContext(Dispatchers.IO){
            database.update(night)
        }
    }

    fun onClearTracking(){
        uiScope.launch {
            _showSnackbarEvent.value = true
            tonight.value = null
            clear()
        }
    }

    private suspend fun clear(){
        withContext(Dispatchers.IO){
            database.clear()
        }
    }

    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    val showSnackbarEvent:LiveData<Boolean>

    get() = _showSnackbarEvent

    fun doneShowingSnackbar(){
        _showSnackbarEvent.value = false
    }


}