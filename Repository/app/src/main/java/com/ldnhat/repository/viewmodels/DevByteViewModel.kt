package com.ldnhat.repository.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.ldnhat.repository.database.getDatabase
import com.ldnhat.repository.domain.DevByteVideos
import com.ldnhat.repository.network.DevByteNetwork
import com.ldnhat.repository.network.asDomainModel
import com.ldnhat.repository.repos.VideoRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

class DevByteViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * A playlist of videos that can be shown on the screen. Views should use this to get access
     * to the data.
     */
    private val videoRepository = VideoRepository(getDatabase(application))
    val playlist = videoRepository.videos
    /**
     * Event triggered for network error. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    /**
     * Event triggered for network error. Views should use this to get access
     * to the data.
     */
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    /**
     * Flag to display the error message. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    /**
     * Flag to display the error message. Views should use this to get access
     * to the data.
     */
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown




    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        refreshDataFromRepository()
    }

    /**
     * Refresh data from network and pass it via LiveData. Use a coroutine launch to get to
     * background thread.
     */
//    private fun refreshDataFromNetwork() = viewModelScope.launch {
//
//        try {
//            val playlist = DevByteNetwork.devbytes.getPlayList()
//            _playlist.postValue(playlist.asDomainModel())
//
//            _eventNetworkError.value = false
//            _isNetworkErrorShown.value = false
//
//        } catch (networkError: IOException) {
//            // Show a Toast error message and hide the progress bar.
//            delay(2000)
//            _eventNetworkError.value = true
//        }
//    }


    private fun refreshDataFromRepository(){
        viewModelScope.launch {
            try {
                videoRepository.refreshVideos()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            }catch (networkError : Exception){
                if (playlist.value.isNullOrEmpty()){
                    _eventNetworkError.value = true
                }
            }
        }
    }

    /**
     * Resets the network error flag.
     */
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DevByteViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DevByteViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}