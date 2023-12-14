package com.mertg.kotlincountries.service

import com.mertg.kotlincountries.model.Country
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface CountryAPI {
    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries(): Single<List<Country>> //Obersable also ok, Single = value guaranteed
}