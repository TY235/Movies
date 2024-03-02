package com.tzeyi.movies.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tzeyi.movies.data.repository.MovieRepository
import com.tzeyi.movies.utilities.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {
    private val _movieDetails = MutableStateFlow<Result<*>>(Result.Loading)
    val movieDetails = _movieDetails.asStateFlow()

    fun getMovieDetails(imdbId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getMovieDetailsById(imdbId)
            _movieDetails.update { result }
        }
    }
}
