package dev.art.rtl.countries.ui.countries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import dev.art.rtl.countries.R
import dev.art.rtl.countries.data.model.Country
import dev.art.rtl.countries.databinding.FragmentCountryInfoBinding
import dev.art.rtl.countries.ui.viewModel.CountryInfoViewModel
import dev.art.rtl.countries.utils.Constants
import dev.art.rtl.countries.utils.Resource

@AndroidEntryPoint
class CountryInfoFragment : Fragment() {

    private val viewModel: CountryInfoViewModel by viewModels()
    private lateinit var binding: FragmentCountryInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        CountryInfoViewModel.cName = arguments?.getString(Constants.COUNTRY_NAME_KEY).toString()

        binding = FragmentCountryInfoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun getData() {
        viewModel.countryInfoResponse.observe(viewLifecycleOwner) {

            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.pbCountryInfo.visibility = View.VISIBLE
                }

                Resource.Status.SUCCESS -> {
                    binding.pbCountryInfo.visibility = View.GONE

                    it.data!!.forEach { country ->
                        val cName = country.name.common
                        val cCapital = country.capital
                        val cRegion = country.region
                        val cSubRegion = country.subregion
                        val cLanguages = country.languages.values
                        val cBorders = country.borders.toString()
                        val cFlags = country.flags.png + " " + country.flags.svg
                        val cCoatOfArms = country.coatOfArms.png + " " + country.coatOfArms.svg

                        binding.tvCountryInfo.text =
                            getString(
                                R.string.country_details, cName, cCapital,
                                cRegion, cSubRegion, cLanguages, cBorders, cFlags, cCoatOfArms
                            )

                        Glide
                            .with(this@CountryInfoFragment)
                            .load(country.coatOfArms.png)
                            .centerCrop()
                            .placeholder(R.drawable.img_holder)
                            .into(binding.ivCountryInfo)
                    }
                }

                Resource.Status.ERROR -> {
                    binding.pbCountryInfo.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }

        }
    }

    companion object {
        fun newInstance(name: String) =
            CountryInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.COUNTRY_NAME_KEY, name)
                }
            }
    }
}