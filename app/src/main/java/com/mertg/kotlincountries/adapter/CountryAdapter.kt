    package com.mertg.kotlincountries.adapter

    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.ImageView
    import android.widget.TextView
    import androidx.databinding.DataBindingUtil
    import androidx.navigation.Navigation
    import androidx.recyclerview.widget.RecyclerView
    import com.mertg.kotlincountries.R
    import com.mertg.kotlincountries.databinding.ItemCountryBinding
    import com.mertg.kotlincountries.model.Country
    import com.mertg.kotlincountries.util.downloadFromURL
    import com.mertg.kotlincountries.util.placeholderProgressBar
    import com.mertg.kotlincountries.view.FeedFragmentDirections

    class CountryAdapter(val countryList : ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), CountryClickListener
    {
        class CountryViewHolder(var view : ItemCountryBinding) : RecyclerView.ViewHolder(view.root) {
//            val nameTextView: TextView = view.findViewById(R.id.name)
//            val regionTextView: TextView = view.findViewById(R.id.region)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
//            val view = inflater.inflate(R.layout.item_country,parent,false)

            val view = DataBindingUtil.inflate<ItemCountryBinding>(inflater,R.layout.item_country,parent,false)

            return CountryViewHolder(view)
        }

        override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

            holder.view.country = countryList[position]
            holder.view.listener = this

            /*
            // Burada textViewCountryName'e erişebilirsiniz. Databinding'den öncesi.
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
            */
        }

        override fun getItemCount(): Int {
            return countryList.size
        }

        override fun onCountryClicked(view: View) {
            var countryUuidText = view.findViewById<TextView>(R.id.countryUUIDText)
            val uuid = countryUuidText.text.toString().toInt()
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(uuid)
            //action.countryUuid
            Navigation.findNavController(view).navigate(action)
        }

        fun updateCountryList(newCountryList : List<Country> ){
            countryList.clear()
            countryList.addAll(newCountryList)
            notifyDataSetChanged()
        }



    }


