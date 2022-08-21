package dev.art.rtl.countries.ui.countries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.art.rtl.countries.R
import dev.art.rtl.countries.databinding.FragmentCountriesBinding
import dev.art.rtl.countries.ui.adapter.CountriesAdapter
import dev.art.rtl.countries.ui.viewModel.CountriesViewModel
import dev.art.rtl.countries.utils.Constants
import dev.art.rtl.countries.utils.Resource

@AndroidEntryPoint
class CountriesFragment : Fragment() {

    private lateinit var binding: FragmentCountriesBinding
    private lateinit var adapter: CountriesAdapter

    private val viewModel: CountriesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        binding = FragmentCountriesBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.rvCountries.layoutManager = LinearLayoutManager(activity)
        adapter = CountriesAdapter(listOf())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.onCountryClickListener = object : CountriesAdapter.CountryClickListener {
            override fun onCountryClicked(name: String) {
                goToCountryInfoFragment(name)
            }
        }

        observeData()
    }

    private fun observeData() {
        viewModel.countriesResponse.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.pbCountries.visibility = View.VISIBLE
                }

                Resource.Status.SUCCESS -> {
                    binding.pbCountries.visibility = View.GONE
                    adapter.setItems(it.data!!)
                    binding.rvCountries.adapter = adapter
                }

                Resource.Status.ERROR -> {
                    // TODO: show error msg 
                    binding.pbCountries.visibility = View.GONE
                }
            }
        }
    }

    // TODO: use newInstance() of countryInfoFragment to pass name 
    private fun goToCountryInfoFragment(name: String = "saudi") {

        val bundle = Bundle()
        bundle.putString(Constants.COUNTRY_NAME_KEY, name)

        val countryInfoFragment = CountryInfoFragment()
        countryInfoFragment.arguments = bundle

        parentFragmentManager.beginTransaction().add(R.id.fragment_container, countryInfoFragment)
            .addToBackStack("CountryInfoFragment")
            .commit()
    }
}