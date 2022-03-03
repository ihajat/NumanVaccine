package com.example.numanvaccine.data.room.mappers

import com.example.numanvaccine.data.room.model.CountryEntity
import com.example.numanvaccine.domain.model.Country
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CacheMapperTest {

    val cacheMapper = CacheMapper()
    val countryEntity = CountryEntity(
        1,
        1,
        1,
        1,
        "United Kingdom",
        1,
        1,
        "",
        "na",
        "Europe",
        "GB",
        "na",
        0,
        "London",
        "na",
        0
    )
    val country = Country(
        1,
        1,
        1,
        1,
        "United Kingdom",
        1,
        1,
        "",
        "na",
        "Europe",
        "GB",
        "na",
        0,
        "London",
        "na",
        0
    )

    val countryEntitys = listOf(countryEntity)
    val countrys = listOf(country)

    @Before
    fun setUp() {
    }

    @Test
    fun `test the correctness of conversion from domain single model to entity`() {
        assertEquals(countryEntity, cacheMapper.mapToData(country))
    }

    @Test
    fun `test the correctness of conversion from entity to domain single model`() {
        assertEquals(country, cacheMapper.mapFromData(countryEntity))
    }

    @Test
    fun `test the correctness of conversion from list of entity's to domain countrys`() {
        assertEquals(countrys, cacheMapper.mapFromDataList(countryEntitys))
    }
}