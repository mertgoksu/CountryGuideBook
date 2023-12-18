    package com.mertg.kotlincountries.adapter

    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.ImageView
    import android.widget.TextView
    import androidx.navigation.Navigation
    import androidx.recyclerview.widget.RecyclerView
    import com.mertg.kotlincountries.R
    import com.mertg.kotlincountries.model.Country
    import com.mertg.kotlincountries.util.downloadFromURL
    import com.mertg.kotlincountries.util.placeholderProgressBar
    import com.mertg.kotlincountries.view.FeedFragmentDirections

    class CountryAdapter(val countryList : ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>()
    {
        inner class CountryViewHolder(var view : View) : RecyclerView.ViewHolder(view) {
            val nameTextView: TextView = view.findViewById(R.id.name)
            val regionTextView: TextView = view.findViewById(R.id.region)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item_country,parent,false)
            return CountryViewHolder(view)
        }

        override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
            // Burada textViewCountryName'e erişebilirsiniz.
            holder.nameTextView.text = countryList[position].countryName
            holder.regionTextView.text = countryList[position].countryRegion

            holder.view.setOnClickListener {
                val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
                //action.countryUuid
                Navigation.findNavController(it).navigate(action)
            }

            holder.view.findViewById<ImageView>(R.id.imageView).downloadFromURL(countryList[position].imageURL,
                placeholderProgressBar(holder.view.context)
            )
        }

        override fun getItemCount(): Int {
            return countryList.size
        }

        fun updateCountryList(newCountryList : List<Country> ){
            countryList.clear()
            countryList.addAll(newCountryList)
            notifyDataSetChanged()
        }

    }


