package dev.art.rtl.countries.ui.countries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
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

    // TODO: use motion layout

    private lateinit var binding: FragmentCountriesBinding
    private lateinit var countriesAdapter: CountriesAdapter

    private val viewModel: CountriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCountriesBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.rvCountries.layoutManager = LinearLayoutManager(activity)
        countriesAdapter = CountriesAdapter(listOf())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countriesAdapter.onCountryClickListener = object : CountriesAdapter.CountryClickListener {
            override fun onCountryClicked(name: String, recyclerItemContainer: ConstraintLayout) {
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
                    countriesAdapter.setItems(it.data!!)
                    binding.rvCountries.adapter = countriesAdapter
                }

                Resource.Status.ERROR -> {
                    binding.pbCountries.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun goToCountryInfoFragment(name: String) {

        parentFragmentManager.beginTransaction()
            .add(R.id.fragment_container, CountryInfoFragment.newInstance(name))
            .addToBackStack("CountryInfoFragment")
            .commit()
    }
}