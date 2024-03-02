package com.tzeyi.movies.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tzeyi.movies.data.entity.Movie
import com.tzeyi.movies.data.repository.MovieRepository
import com.tzeyi.movies.utilities.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(repository: MovieRepository) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _movies = MutableStateFlow(listOf<Movie>())
    val movies =
        searchText
            .onEach { _isSearching.update { true } }
            .combine(_movies) { text, movie ->
                if (text.isBlank()) {
                    movie
                } else {
                    movie.filter { it.doesMatchSearchQuery(text) }
                }
            }
            .onEach { _isSearching.update { false } }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _movies.value)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovies().collect { result ->
                when (result) {
                    is Result.Success -> _movies.update { result.data }
                    is Result.Error -> Log.w("Network Error", result.exception)
                    is Result.Loading -> Unit
                }
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}
