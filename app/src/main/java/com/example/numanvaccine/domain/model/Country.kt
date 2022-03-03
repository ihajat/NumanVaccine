package com.example.numanvaccine.domain.model
import java.io.Serializable

data class Country (
    val id: Long,
    val administered: Int,
    val peopleVaccinated: Int,
    val peoplePartiallyVaccinated: Int,
    val country: String,
    val population: Int,
    val sqKmArea: Int,
    val lifeExpectancy: String,
    val elevationInMeters: String,
    val continent: String,
    val abbreviation: String,
    val location: String,
    val iso: Int,
    val capitalCity: String,
    val updated: String,
    var favourite: Int

): Serializable