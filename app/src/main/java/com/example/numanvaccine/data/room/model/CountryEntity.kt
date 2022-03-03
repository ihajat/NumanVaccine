package com.example.numanvaccine.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "administered")
    val administered: Int,

    @ColumnInfo(name = "peopleVaccinated")
    val peopleVaccinated: Int,

    @ColumnInfo(name = "peoplePartiallyVaccinated")
    val peoplePartiallyVaccinated: Int,

    @ColumnInfo(name = "country")
    val country: String,

    @ColumnInfo(name = "population")
    val population: Int,

    @ColumnInfo(name = "sqKmArea")
    val sqKmArea: Int,

    @ColumnInfo(name = "lifeExpectancy")
    val lifeExpectancy: String,

    @ColumnInfo(name = "elevationInMeters")
    val elevationInMeters: String,

    @ColumnInfo(name = "continent")
    val continent: String,

    @ColumnInfo(name = "abbreviation")
    val abbreviation: String,

    @ColumnInfo(name = "location")
    val location: String,

    @ColumnInfo(name = "iso")
    val iso: Int,

    @ColumnInfo(name = "capitalCity")
    val capitalCity: String,

    @ColumnInfo(name = "updated")
    val updated: String,

    @ColumnInfo(name = "favourite")
    val favourite: Int


)