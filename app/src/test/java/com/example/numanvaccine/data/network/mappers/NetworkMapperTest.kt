package com.example.numanvaccine.data.network.mappers

import com.example.numanvaccine.data.network.model.CountryDto
import com.example.numanvaccine.domain.model.Country
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NetworkMapperTest {

    val networkMapper = NetworkMapper()
    val countryDto = CountryDto(
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
        "na"

    )
    val country = Country(
        0,
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

    val countryDtos = listOf(countryDto)
    val countrys = listOf(country)

    @Before
    fun setUp() {
    }

    @Test
    fun `test the correctness of conversion from domain single model to Dto`() {
        assertEquals(countryDto, networkMapper.mapToData(country))
    }

    @Test
    fun `test the correctness of conversion from Dto to domain single model`() {
        assertEquals(country, networkMapper.mapFromData(countryDto))
    }

    @Test
    fun `test the correctness of conversion from list of Dto's to demain countrys`() {
        assertEquals(countrys, networkMapper.mapFromDataList(countryDtos))
    }
}