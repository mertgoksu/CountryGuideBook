package com.mertg.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mertg.kotlincountries.R
import com.mertg.kotlincountries.adapter.CountryAdapter
import com.mertg.kotlincountries.viewmodel.FeedViewModel

class FeedFragment : Fragment() {
    private lateinit var viewModel : FeedViewModel
//    private val countryAdapter = CountryAdapter(arrayListOf())

    private lateinit var countryList : RecyclerView
    private lateinit var countryError : TextView
    private lateinit var countryLoading : ProgressBar

    private lateinit var recyclerView: RecyclerView
    private lateinit var countryAdapter: CountryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)

        countryError = view.findViewById(R.id.countryError)
        countryLoading = view.findViewById(R.id.countryLoadingProgressbar)

        recyclerView = view.findViewById(R.id.countryList)
        countryAdapter = CountryAdapter(arrayListOf())

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = countryAdapter

        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        viewModel.refreshData()

        refreshLayout.setOnRefreshListener {
            recyclerView.visibility = View.GONE
            countryError.visibility = View.GONE
            countryLoading.visibility = View.VISIBLE
            viewModel.refreshData()
            refreshLayout.isRefreshing = false
        }

        observeLiveData()



//        countryList?.layoutManager = LinearLayoutManager(context)
//        countryList?.adapter = countryAdapter



        /*val goCountryButton : Button = view.findViewById(R.id.goCoutryButton)

        // Buton'a tıklama işlemlerini eklemek istiyorsanız:
        goCountryButton.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }*/
    }

    fun observeLiveData() {
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                recyclerView.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if(it) {
                    countryError.visibility = View.VISIBLE
                } else {
                    countryError.visibility = View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    countryLoading.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    countryError.visibility = View.GONE
                } else {
                    countryLoading.visibility = View.GONE
                }
            }
        })
    }




}