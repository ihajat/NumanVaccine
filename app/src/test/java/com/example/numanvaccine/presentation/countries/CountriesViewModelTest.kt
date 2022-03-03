package com.example.numanvaccine.presentation.countries

import com.example.numanvaccine.data.repository.VaccinatedRepository
import com.example.numanvaccine.domain.model.Country
import com.example.numanvaccine.presentation.ui.recipecollection.CountriesViewModel
import com.example.numanvaccine.presentation.ui.recipecollection.CountriesViewState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CountryListViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var countriesViewModel: CountriesViewModel
    private val vaccinatedRepository: VaccinatedRepository = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given collections are loaded When data source is success Then emit success view state`() {
        val expectedCollections = getCollections()
        runTest {
            whenever(vaccinatedRepository.getCountries()).thenReturn(expectedCollections)
            countriesViewModel = CountriesViewModel(vaccinatedRepository)

            val stateFlow = countriesViewModel.viewState.first()

            assertEquals(stateFlow, CountriesViewState.Success(expectedCollections))
        }
    }

    @Test
    fun `Given collections are loaded When data source is error Then emit error view state`() {
        runTest {
            whenever(vaccinatedRepository.getCountries()).thenThrow(RuntimeException(""))
            countriesViewModel = CountriesViewModel(vaccinatedRepository)

            val stateFlow = countriesViewModel.viewState.first()

            assertEquals(stateFlow, CountriesViewState.Error)
        }
    }

    private fun getCollections(): List<Country> {
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
        return listOf(country)
    }
}