package dev.art.rtl.countries.data.repository

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.art.rtl.countries.R
import dev.art.rtl.countries.data.model.Country
import dev.art.rtl.countries.data.remote.ApiInterface
import dev.art.rtl.countries.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CountriesRepository @Inject constructor
    (
    private val apiInterface: ApiInterface,
    @ApplicationContext val context: Context
) {
    //private val _allCountries = MutableSharedFlow<Resource<List<Country>>>()
    //private val _countryInfo = MutableSharedFlow<Resource<List<Country>>>()

    //region allCountries
    suspend fun getAllCountries(): Flow<Resource<List<Country>>> = flow {

        emit(Resource.loading())

        val response = apiInterface.getAllCountries()

        if (response.body() != null) {
            emit(Resource.success(response.body()!!))

        } else {
            emit(Resource.error(context.getString(R.string.error_api_msg)))
        }
    }

    //endregion

    //region CountryInfo
    suspend fun getCountryInfo(name: String): Flow<Resource<List<Country>>> = flow {

        emit(Resource.loading())

        val response = apiInterface.getOneCountry(name)

        if (response.body() != null) {
            emit(Resource.success(response.body()!!))

        } else {
            emit(Resource.error(context.getString(R.string.error_api_msg)))
        }
    }

    //endregion

}