package com.example.numanvaccine.presentation.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.numanvaccine.R
import com.example.numanvaccine.databinding.CountryListItemBinding
import com.example.numanvaccine.domain.model.Country
import com.example.numanvaccine.domain.model.CountryCodes
import com.example.numanvaccine.presentation.common.blueColor
import com.example.numanvaccine.presentation.common.coloredText
import com.example.numanvaccine.presentation.common.greenColor
import com.example.numanvaccine.presentation.common.redColor
import com.example.numanvaccine.presentation.util.million

class CountriesAdapter(var clickListner: OnCountryItemClickListner) :
    ListAdapter<Country, CountriesAdapter.CountryListViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        val countryListItemBinding =
            CountryListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CountryListViewHolder(countryListItemBinding)
    }

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {
        holder.bind(getItem(position), clickListner)
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class CountryListViewHolder(private val binding: CountryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Country, action: OnCountryItemClickListner) {
            if (item.country.isNotEmpty()) {

                Glide.with(binding.root.context)
                    .load(
                        "https://flagcdn.com/256x192/${
                            CountryCodes.getCountryCode(item.country).code.lowercase()
                        }.png"
                    )
                    .centerCrop()
                    .error(R.drawable.not_found)
                    .placeholder(R.drawable.loading)
                    .into(binding.countryFlag)

                binding.moreTextView.setOnClickListener {
                    action.onItemClicked(item, adapterPosition, item.favourite)
                }
                binding.countryNameTextView.text = item.country
                if (item.favourite == 1) {
                    binding.favouriteImageView.setImageResource(R.drawable.ic_baseline_favorite_24)
                    binding.favouriteImageView.setTag(0)
                } else {
                    binding.favouriteImageView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    binding.favouriteImageView.setTag(1)
                }
                binding.favouriteImageView.setOnClickListener {
                    if (item.favourite == 1) {
                        binding.favouriteImageView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                        binding.favouriteImageView.setTag(0)
                        currentList.get(absoluteAdapterPosition).favourite = 0
                        item.favourite = 0
                    } else {
                        binding.favouriteImageView.setImageResource(R.drawable.ic_baseline_favorite_24)
                        binding.favouriteImageView.setTag(1)
                        currentList.get(absoluteAdapterPosition).favourite = 1
                        item.favourite = 1
                    }
                    action.onFavouriteClick(item, adapterPosition, item.favourite)
                }
                val population = "${million(item.population)}".blueColor
                val administered = "${million(item.administered)}".redColor
                val peopleVaccinated = "${million(item.peopleVaccinated)}".greenColor

                val descripton =
                    "A population of " + population + ", administered: " + administered + ", vaccinated: " + peopleVaccinated
                binding.descriptionTextView.coloredText = descripton
            }


        }
    }

    interface OnCountryItemClickListner {
        fun onFavouriteClick(item: Country, position: Int, add: Int)
        fun onItemClicked(item: Country, position: Int, add: Int)
    }
}

