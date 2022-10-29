package dev.art.rtl.countries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.art.rtl.countries.R
import dev.art.rtl.countries.data.model.Country
import dev.art.rtl.countries.databinding.RecyclerItemCountryBinding

class CountriesAdapter(private var countiesList: List<Country>) :
    RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    lateinit var onCountryClickListener: CountryClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = RecyclerItemCountryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = countiesList[position].name.common
        holder.desc.text = countiesList[position].region

        //load to imageView
        Glide
            .with(holder.itemContainer.context)
            .load(countiesList[position].flags.png)
            .centerCrop()
            .placeholder(R.drawable.img_holder)
            .into(holder.imageView)

        holder.itemContainer.setOnClickListener {
            onCountryClickListener.onCountryClicked(
                countiesList[position].name.common,
                holder.itemContainer
            )
        }
    }

    override fun getItemCount() = countiesList.size

    inner class ViewHolder(private val binding: RecyclerItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val itemContainer = binding.recyclerItemRoot
        val title = binding.tvTitle
        val desc = binding.tvDesc
        val imageView = binding.ivCountry
    }

    // to be updated
    fun setItems(items: List<Country>) {
        this.countiesList = items
        notifyDataSetChanged()
    }

    interface CountryClickListener {
        fun onCountryClicked(name: String, recyclerItemContainer: ConstraintLayout)
    }
}