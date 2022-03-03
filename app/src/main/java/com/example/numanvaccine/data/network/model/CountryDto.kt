package com.example.numanvaccine.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("administered") @Expose var administered: Int = 0,
    @SerializedName("people_vaccinated") @Expose var peopleVaccinated: Int = 0,
    @SerializedName("people_partially_vaccinated") @Expose var peoplePartiallyVaccinated: Int = 0,
    @SerializedName("country") @Expose var country: String = "",
    @SerializedName("population") @Expose var population: Int = 0,
    @SerializedName("sq_km_area") @Expose var sqKmArea: Int = 0,
    @SerializedName("life_expectancy") @Expose var lifeExpectancy: String = "",
    @SerializedName("elevation_in_meters") @Expose var elevationInMeters: String = "",
    @SerializedName("continent") @Expose var continent: String = "",
    @SerializedName("abbreviation") @Expose var abbreviation: String = "",
    @SerializedName("location") @Expose var location: String = "",
    @SerializedName("iso") @Expose var iso: Int = 0,
    @SerializedName("capital_city") @Expose var capitalCity: String = "",
    @SerializedName("updated") @Expose var updated: String = ""
)