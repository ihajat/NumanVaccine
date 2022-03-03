package com.example.numanvaccine.domain.model

object CountryCodes {
    val map = mutableMapOf<String, CountryCode>()

    fun getCountryCode(country: String): CountryCode {
        var countryFound: CountryCode = (map[country] ?: map["United Kingdom"]) as CountryCode
        return countryFound
    }

}

