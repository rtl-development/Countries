package dev.art.rtl.countries.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.art.rtl.countries.data.remote.ApiInterface
import dev.art.rtl.countries.data.repository.CountriesRepository
import dev.art.rtl.countries.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountriesModule {

    @Singleton
    @Provides
    fun provideCountriesRepository(
        apiInterface: ApiInterface,
        @ApplicationContext applicationContext: Context
    ): CountriesRepository =
        CountriesRepository(apiInterface, applicationContext)
}