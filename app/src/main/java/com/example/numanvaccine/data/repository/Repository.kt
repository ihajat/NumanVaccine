package com.example.numanvaccine.data.repository

import com.example.numanvaccine.domain.model.Country

interface Repository {
    suspend fun getCountries(): List<Country>
    suspend fun Update(country: Country)
    suspend fun getFavouriteList(): List<Country>
}