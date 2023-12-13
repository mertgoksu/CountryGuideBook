package com.mertg.kotlincountries.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mertg.kotlincountries.R
import com.mertg.kotlincountries.model.Country

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
        //BASE_URL -> https://raw.githubusercontent.com/
        //EXT -> atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    }
}