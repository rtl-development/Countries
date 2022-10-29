package dev.art.rtl.countries.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.art.rtl.countries.data.model.Country
import dev.art.rtl.countries.data.repository.CountriesRepository
import dev.art.rtl.countries.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryInfoViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository) : ViewModel() {

    companion object{
        lateinit var cName: String
    }

    private var _response = MutableLiveData<Resource<List<Country>>>()
    val countryInfoResponse: LiveData<Resource<List<Country>>> = _response

    private val countryName = cName

    init {
        getOneCountry(countryName)
    }

    private fun getOneCountry(name: String) {
        viewModelScope.launch {
            countriesRepository.getCountryInfo(name).collect {
                _response.postValue(it)
            }
        }
    }
}