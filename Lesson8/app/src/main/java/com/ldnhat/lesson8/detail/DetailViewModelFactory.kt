package com.ldnhat.lesson8.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ldnhat.lesson8.network.MarsPropety

class DetailViewModelFactory(private val application: Application,
                             private val marsPropety: MarsPropety) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(marsPropety, application) as T
        }
        throw IllegalArgumentException()
    }
}