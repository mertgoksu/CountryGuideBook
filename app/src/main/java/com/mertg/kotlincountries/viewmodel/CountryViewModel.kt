package com.mertg.kotlincountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mertg.kotlincountries.model.Country

class CountryViewModel : ViewModel() {
    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom() {
        val country = Country("Turkey","Eu","Ankara","Try", "Turkish","www.ss.com")
        countryLiveData.value = country
    }
}