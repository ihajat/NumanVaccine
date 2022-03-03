package com.example.numanvaccine.data.di

import com.example.numanvaccine.data.network.model.VaccinatedCountriesDto
import com.example.numanvaccine.data.network.services.VaccinatedCountriesService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
//    private const val BASE_URL = "https://covid-api.mmediagroup.fr/"

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .setLenient()
            .registerTypeAdapter(VaccinatedCountriesDto::class.java, CountryListDeserializer())
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }


    @Singleton
    @Provides
    fun provideRetrofit(customGson: Gson): Retrofit.Builder { //, baseUrl: String
        return Retrofit.Builder()
            .baseUrl(UrlModule.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(customGson))
    }

    @Singleton
    @Provides
    fun provideBlogServiceAPI(retrofit: Retrofit.Builder): VaccinatedCountriesService {
        return retrofit
            .build()
            .create(VaccinatedCountriesService::class.java)
    }
}