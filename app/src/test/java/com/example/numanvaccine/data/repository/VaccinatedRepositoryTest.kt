package com.example.numanvaccine.data.repository

import com.example.numanvaccine.data.network.mappers.NetworkMapper
import com.example.numanvaccine.data.network.model.CountryDto
import com.example.numanvaccine.data.network.model.VaccinatedCountriesDto
import com.example.numanvaccine.data.network.services.VaccinatedCountriesService
import com.example.numanvaccine.data.room.db.CountryDao
import com.example.numanvaccine.data.room.mappers.CacheMapper
import com.example.numanvaccine.data.room.model.CountryEntity
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class VaccinatedRepositoryTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private val countryDao = mock<CountryDao>()
    private val apiInterface = mock<VaccinatedCountriesService>()
    private val cacheMapper = CacheMapper()
    private val networkMapper = NetworkMapper()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when remote api returns success Then repo should also return success with correct mapping`() {
        val collectionEntity = geCountriesEntity()
        val vaccinatedCountriesDto = getVaccinatedCountriesDto()
        runTest {
            whenever(countryDao.getAll()).thenReturn(collectionEntity)
            whenever(apiInterface.getCountries()).thenReturn(vaccinatedCountriesDto)

            val vaccinatedRepository = VaccinatedRepository(countryDao, apiInterface, cacheMapper, networkMapper)
            val countriesList = vaccinatedRepository.getCountries()

            assertEquals(countriesList.size, vaccinatedCountriesDto.alls.size)
            assertEquals(countriesList.first().country, vaccinatedCountriesDto.alls.first().country)
        }
    }

    private fun geCountriesEntity(): List<CountryEntity> {
        return listOf(
            CountryEntity(
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
        )
    }

    private fun getVaccinatedCountriesDto(): VaccinatedCountriesDto {

        val alls = listOf(
            CountryDto(
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
        )
        var vaccinatedCountriesDto =  VaccinatedCountriesDto()
        vaccinatedCountriesDto.alls = alls
        return vaccinatedCountriesDto
    }
}
