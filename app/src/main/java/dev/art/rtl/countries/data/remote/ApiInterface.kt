package dev.art.rtl.countries.data.remote
import dev.art.rtl.countries.data.model.Country
import dev.art.rtl.countries.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET(Constants.COUNTRIES_LIST_PATH)
    suspend fun getAllCountries(): Response<List<Country>>

    @GET(Constants.ONE_COUNTRY_PATH)
    suspend fun getOneCountry(@Path(Constants.COUNTRY_NAME) countryName: String): Response<List<Country>>
}