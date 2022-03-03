package com.example.numanvaccine.presentation.favourites


import com.example.numanvaccine.data.repository.VaccinatedRepository
import com.example.numanvaccine.domain.model.Country
import com.example.numanvaccine.presentation.ui.recipefavourites.FavouriteListViewModel
import com.example.numanvaccine.presentation.ui.recipefavourites.FavouriteListViewState
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
class FavouriteListViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var favouriteListViewModel: FavouriteListViewModel
    private val vaccinatedRepository:VaccinatedRepository = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given favourites are loaded When data source is success Then emit success view state`() {
        val expectedFavourites = getFavourites()
        runTest {
            whenever(vaccinatedRepository.getFavouriteList()).thenReturn(expectedFavourites)
            favouriteListViewModel = FavouriteListViewModel(vaccinatedRepository)

            val stateFlow = favouriteListViewModel.viewState.first()

            assertEquals(stateFlow, FavouriteListViewState.Success(expectedFavourites))
        }
    }

    @Test
    fun `Given favourites are loaded When data source is error Then emit error view state`() {
        runTest {
            whenever(vaccinatedRepository.getFavouriteList()).thenThrow(RuntimeException(""))
            favouriteListViewModel = FavouriteListViewModel(vaccinatedRepository)

            val stateFlow = favouriteListViewModel.viewState.first()

            assertEquals(stateFlow, FavouriteListViewState.Error)
        }
    }

    private fun getFavourites(): List<Country> {
        val element = Country(
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
        return listOf(element)
    }
}