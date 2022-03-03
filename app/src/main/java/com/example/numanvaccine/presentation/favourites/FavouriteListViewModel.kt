package com.example.numanvaccine.presentation.ui.recipefavourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numanvaccine.data.repository.Repository
import com.example.numanvaccine.domain.model.Country
import com.example.numanvaccine.presentation.ui.recipefavourites.FavouriteListViewState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteListViewModel @Inject constructor(
    private val repository: Repository
) :
    ViewModel() {

    private val _viewState = MutableStateFlow<FavouriteListViewState>(
        Success(
            emptyList()
        )
    )
    val viewState: StateFlow<FavouriteListViewState> = _viewState

    init {
        loadFavourites()
    }

    fun refresh() {
        loadFavourites()
    }

    fun Update(recipe: Country){
        viewModelScope.launch {
            repository.Update(recipe)
        }
    }

    private fun loadFavourites() {
        _viewState.value = Loading

        viewModelScope.launch {
            runCatching {
                repository.getFavouriteList()
            }.onFailure {
                _viewState.value = Error
            }.onSuccess { favourites ->
                _viewState.value = Success(favourites)
            }
        }
    }
}

sealed class FavouriteListViewState {
    object Loading : FavouriteListViewState()
    object Error : FavouriteListViewState()
    data class Success(val favourites: List<Country>) : FavouriteListViewState()
}