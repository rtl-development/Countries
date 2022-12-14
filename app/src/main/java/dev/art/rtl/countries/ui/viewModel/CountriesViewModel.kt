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
class CountriesViewModel @Inject constructor(private val countriesRepository: CountriesRepository)
    : ViewModel() {

    private var _response = MutableLiveData<Resource<List<Country>>>()
    val countriesResponse: LiveData<Resource<List<Country>>> = _response

    init {
        getCountries()
    }

    private fun getCountries() {
        viewModelScope.launch {
            countriesRepository.getAllCountries().collect {
                _response.postValue(it)
            }
        }
    }
}