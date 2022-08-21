package dev.art.rtl.countries.ui.countries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import dev.art.rtl.countries.R
import dev.art.rtl.countries.databinding.FragmentCountryInfoBinding
import dev.art.rtl.countries.ui.viewModel.CountryInfoViewModel
import dev.art.rtl.countries.utils.Constants
import dev.art.rtl.countries.utils.Resource

@AndroidEntryPoint
class CountryInfoFragment : Fragment() {

    private val viewModel: CountryInfoViewModel by viewModels()
    private lateinit var binding: FragmentCountryInfoBinding

    private lateinit var cName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        cName = arguments?.getString(Constants.COUNTRY_NAME_KEY).toString()
        CountryInfoViewModel.cName = cName

        binding = FragmentCountryInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
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
                        val cRegion = country.region
                        val cSubRegion = country.subregion
                        val cCapital = country.capital
                        val cLanguages = country.languages

                        // TODO: to be update
                        // it is ugly code but just for save time :)
                        binding.tvCountryInfo.text = cName + "\n \n" +
                                cRegion + "\n \n" + cSubRegion + "\n \n" + cCapital + "\n \n" + cLanguages

                        Glide
                            .with(this@CountryInfoFragment)
                            .load(country.coatOfArms.png)
                            .centerCrop()
                            .placeholder(R.drawable.img_holder)
                            .into(binding.ivCountryInfo)
                    }
                }

                Resource.Status.ERROR -> {
                    // TODO: show error msg
                    binding.pbCountryInfo.visibility = View.GONE
                }
            }

        }
    }

    // TODO: receiving country name using this func
    companion object {
        fun newInstance(name: String) =
            CountryInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.COUNTRY_NAME_KEY, name)
                }
            }
    }

}