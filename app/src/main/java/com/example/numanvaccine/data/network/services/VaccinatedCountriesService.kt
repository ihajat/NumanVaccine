package com.example.numanvaccine.data.network.services

import com.example.numanvaccine.data.network.model.VaccinatedCountriesDto
import retrofit2.http.GET

interface VaccinatedCountriesService {

    @GET("v1/vaccines")
    suspend fun getCountries(): VaccinatedCountriesDto

}