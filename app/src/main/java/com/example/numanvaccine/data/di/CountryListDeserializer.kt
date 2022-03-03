package com.example.numanvaccine.data.di

import com.example.numanvaccine.data.network.model.CountryDto
import com.example.numanvaccine.data.network.model.VaccinatedCountriesDto
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.json.JSONObject
import java.lang.reflect.Type

class CountryListDeserializer : JsonDeserializer<VaccinatedCountriesDto> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): VaccinatedCountriesDto {

        println("inside deserialize")
        val jsonString = json.toString()
        println(jsonString)
        val jsonObject: JSONObject = JSONObject(jsonString)
        println(jsonObject)
        val countries = mutableListOf<CountryDto>()
        // Get all the keys in the object and convert them to a List
        val keys = jsonObject.keys().asSequence().toList()
        keys.forEach { countryKey ->

            val countryObject = jsonObject.optJSONObject(countryKey as String)!!

            // Get the All object which now has all the data about each country
            val allObject = countryObject.optJSONObject("All")!!

            // Get all the values from the all object

            val administered = allObject.optInt("administered")
            val people_vaccinated = allObject.optInt("people_vaccinated")
            val people_partially_vaccinated = allObject.optInt("people_partially_vaccinated")
            val country = allObject.optString("country")
            val population = allObject.optInt("population")
            val sqKmArea = allObject.optInt("sq_km_area")
            val lifeExpectancy = allObject.optString("life_expectancy")
            val elevationInMeters = allObject.optString("elevation_in_meters")
            val continent = allObject.optString("continent")
            val abbreviation = allObject.optString("abbreviation")
            val location = allObject.optString("location")
            val capitalCity = allObject.optString("capital_city")
            val iso = allObject.optInt("iso")
            val updated = allObject.optString("updated")
            val countryDto = CountryDto(
                administered = administered,
                peopleVaccinated = people_vaccinated,
                peoplePartiallyVaccinated = people_partially_vaccinated,
                country = country,
                population = population,
                sqKmArea = sqKmArea,
                lifeExpectancy = lifeExpectancy,
                elevationInMeters = elevationInMeters,
                continent = continent,
                abbreviation = abbreviation,
                location = location,
                capitalCity = capitalCity,
                iso = iso,
                updated = updated

            )
            countries.add(countryDto)
        }

        var vaccinatedCountries = VaccinatedCountriesDto()
        vaccinatedCountries.alls = countries
        return vaccinatedCountries
    }

}