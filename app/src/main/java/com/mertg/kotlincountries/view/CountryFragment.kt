package com.mertg.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mertg.kotlincountries.R
import com.mertg.kotlincountries.databinding.FragmentCountryBinding
import com.mertg.kotlincountries.util.downloadFromURL
import com.mertg.kotlincountries.util.placeholderProgressBar
import com.mertg.kotlincountries.viewmodel.CountryViewModel


class CountryFragment : Fragment() {
    private lateinit var viewModel : CountryViewModel
    private var countryUUid = 0
    private lateinit var dataBinding: FragmentCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_country,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUUid = CountryFragmentArgs.fromBundle(it).countryUUid
        }

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUUid)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                dataBinding.selectedCountry = country

                /*
                view?.findViewById<TextView>(R.id.countryName)?.text = country.countryName
                view?.findViewById<TextView>(R.id.countryCapital)?.text = country.countryCapital
                view?.findViewById<TextView>(R.id.countryRegion)?.text = country.countryRegion
                view?.findViewById<TextView>(R.id.countryCurrency)?.text = country.countryCurrency
                view?.findViewById<TextView>(R.id.countryLanguage)?.text = country.countryLanguage
                context?.let {
                    view?.findViewById<ImageView>(R.id.countryFlagImage)?.downloadFromURL(country.imageURL, placeholderProgressBar(it))
                }
                */
            }
        })
    }


}