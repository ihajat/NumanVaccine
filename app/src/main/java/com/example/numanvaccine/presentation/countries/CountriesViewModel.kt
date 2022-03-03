package com.example.numanvaccine.presentation.ui.recipecollection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numanvaccine.data.repository.VaccinatedRepository
import com.example.numanvaccine.domain.model.Country
import com.example.numanvaccine.presentation.ui.recipecollection.CountriesViewState.Success
import com.example.numanvaccine.presentation.ui.recipecollection.CountriesViewState.Error
import com.example.numanvaccine.presentation.ui.recipecollection.CountriesViewState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(private val repository: VaccinatedRepository) :
    ViewModel() {

    private val _viewState = MutableStateFlow<CountriesViewState>(Success(emptyList()))
    val viewState: StateFlow<CountriesViewState> = _viewState

    init {
        loadRecipes()
    }

    fun refresh() {
        loadRecipes()
    }

    fun loadRecipes() {
        _viewState.value = Loading

        viewModelScope.launch {
            runCatching {
                repository.getCountries()
            }.onFailure {
                _viewState.value = Error
            }.onSuccess { collection ->
                _viewState.value = Success(collection)
            }
        }
    }

    fun Update(recipe: Country){
        viewModelScope.launch {
            repository.Update(recipe)
        }
    }

}

sealed class CountriesViewState {
    object Loading : CountriesViewState()
    object Error : CountriesViewState()
    data class Success(val recipe: List<Country>) : CountriesViewState()
}