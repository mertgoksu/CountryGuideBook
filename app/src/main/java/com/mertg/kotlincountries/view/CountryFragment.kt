package com.mertg.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mertg.kotlincountries.R
import com.mertg.kotlincountries.viewmodel.CountryViewModel


class CountryFragment : Fragment() {
    private var countryUUid = 0
    private lateinit var viewModel : CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom()

        arguments?.let {
            countryUUid = CountryFragmentArgs.fromBundle(it).countryUUid
        }
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                view?.findViewById<TextView>(R.id.countryName)?.text = country.countryName
                view?.findViewById<TextView>(R.id.countryCapital)?.text = country.countryCapital
                view?.findViewById<TextView>(R.id.countryRegion)?.text = country.countryRegion
                view?.findViewById<TextView>(R.id.countryCurrency)?.text = country.countryCurrency
                view?.findViewById<TextView>(R.id.countryLanguage)?.text = country.countryLanguage
            }
        })
    }


}