package com.example.numanvaccine.presentation.data

import com.example.numanvaccine.domain.model.Country

object DetailedCountryTestData {

    fun getCollections(): List<Country> {
        val country = Country(
            1,
            5412309,
            4125717,
            4907058,
            "Afghanistan",
            35530081,
            652090,
            "45.9",
            "na",
            "Asia",
            "AF",
            "Southern and Central Asia",
            4,
            "Kabul",
            "2022/03/03 00:00:00+00",
            0
        )
        return listOf(country)
    }

    fun getCountry():String {
        return getCollections().get(0).country
    }

    fun getRegion():String {
        return getCollections().get(0).continent
    }

    fun getArea():Int {
        return getCollections().get(0).sqKmArea
    }

    fun getPopulation():Int {
        return getCollections().get(0).population
    }

    fun getAdministered():Int {
        return getCollections().get(0).administered
    }

    fun getPeopleVaccinated():Int {
        return getCollections().get(0).peopleVaccinated
    }
}