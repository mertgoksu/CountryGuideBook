package com.mertg.kotlincountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mertg.kotlincountries.model.Country

class FeedViewModel : ViewModel() {

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val country = Country("Turkey","Eu","Ankara","Try", "Turkish","www.ss.com")
        val country1 = Country("Germany", "EU", "Berlin", "EUR", "German", "www.ss.com")
        val country2 = Country("France", "EU", "Paris", "EUR", "French", "www.ss.com")
        val country3 = Country("Spain", "EU", "Madrid", "EUR", "Spanish", "www.ss.com")

        val countryList = arrayListOf<Country>(country,country1,country2,country3)

        countries.value = countryList
        countryError.value = true
        countryLoading.value = false
    }
}