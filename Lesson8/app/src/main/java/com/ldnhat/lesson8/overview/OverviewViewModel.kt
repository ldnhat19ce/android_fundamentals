package com.ldnhat.lesson8.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ldnhat.lesson8.network.MarsApi
import com.ldnhat.lesson8.network.MarsApiFilter
import com.ldnhat.lesson8.network.MarsPropety
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class MarsApiStatus{
    LOADING, ERROR, DONE
}

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()

    val status:LiveData<MarsApiStatus>
    get() = _status

    private var _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    private var _marsProperty = MutableLiveData<List<MarsPropety>>()

    val marsProperty:LiveData<List<MarsPropety>>
    get() = _marsProperty

    private val _navigateToSelectedProperty = MutableLiveData<MarsPropety>()

    val navigateToSelectedProperty:LiveData<MarsPropety>
    get() = _navigateToSelectedProperty

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        println("viewModel init")
        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)
    }

    private fun getMarsRealEstateProperties(filter: MarsApiFilter){
        try{
            coroutineScope.launch {
                var getPropertiesDeferred = MarsApi.retrofitService.getProperties(filter.value)

                _status.value = MarsApiStatus.LOADING
                var listResult = getPropertiesDeferred.await()
                _status.value = MarsApiStatus.DONE
                if (listResult.isNotEmpty()){
                    _marsProperty.value = listResult
                }

                _response.value =
                    "Success: ${listResult.size} Mars properties retrieved"
            }
        }catch (e : Exception){
            _response.value = "Failure: ${e.message}"
            _status.value = MarsApiStatus.ERROR
            _marsProperty.value = ArrayList()
        }
    }

    fun updateFilter(filter: MarsApiFilter){
        getMarsRealEstateProperties(filter)
    }

    fun displayPropertyDetails(marsProperty: MarsPropety){
        _navigateToSelectedProperty.value = marsProperty
    }

    fun displayPropertyDetailsComplete(){
        _navigateToSelectedProperty.value = null
    }

    override fun onCleared() {
        super.onCleared()
        println("viewModel cleared")
        viewModelJob.cancel()
    }
}