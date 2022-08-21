package dev.art.rtl.countries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import dev.art.rtl.countries.R
import dev.art.rtl.countries.data.model.Country
import dev.art.rtl.countries.databinding.RecyclerItemCountryBinding

class CountriesAdapter(private var items: List<Country>) :
    RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    // TODO: must use view binding here

    lateinit var onCountryClickListener: CountryClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_item_country, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = items[position].name.common
        holder.tvDesc.text = "${items[position].region} - ${items[position].subregion}"
        // holder.ivCountry.setImageResource(R.drawable.img_holder)

        Glide
            .with(holder.itemCotainer.context)
            .load(items[position].flags.png)
            .centerCrop()
            .placeholder(R.drawable.img_holder)
            .into(holder.ivCountry)

        holder.itemCotainer.setOnClickListener {
            //onCountryClickListener.onCountryClicked(items[position].name.common)
            onCountryClickListener.onCountryClicked(items[position].name.common)
        }
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(listItemView: View) :
        RecyclerView.ViewHolder(listItemView) {

        val itemCotainer = listItemView.findViewById<ConstraintLayout>(R.id.recycler_item_root)

        val tvTitle = listItemView.findViewById<TextView>(R.id.tv_title)
        val tvDesc = listItemView.findViewById<TextView>(R.id.tv_desc)

        val ivCountry = listItemView.findViewById<ShapeableImageView>(R.id.iv_country)
    }

    // TODO: Do not use this fun , not good for performance
    fun setItems(items: List<Country>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface CountryClickListener {
        fun onCountryClicked(name: String)
    }
}