package com.example.numanvaccine.data.network.model

import com.google.gson.annotations.SerializedName

data class VaccinatedCountriesDto(

    @SerializedName("alls")
    var alls: List<CountryDto> = emptyList()

)