package com.ldnhat.lesson8.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ldnhat.lesson8.R
import com.ldnhat.lesson8.network.MarsPropety

class DetailViewModel(marsProperty: MarsPropety,
                        app : Application) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<MarsPropety>()

    val selectedProperty:LiveData<MarsPropety>
    get() = _selectedProperty

    val displayPropertyPrice: LiveData<String> = Transformations.map(selectedProperty){
        app.applicationContext.getString(
            when(it.isRental){
                true -> R.string.display_price_monthly_rental
                false -> R.string.display_price
            }, it.price)
    }

    val displayPropertyType: LiveData<String> = Transformations.map(selectedProperty){
        app.applicationContext.getString(R.string.display_type,
            app.applicationContext.getString(
                when(it.isRental){
                    true -> R.string.type_rent
                    false -> R.string.type_sale
                }))
    }

    init {
        _selectedProperty.value = marsProperty
    }


}