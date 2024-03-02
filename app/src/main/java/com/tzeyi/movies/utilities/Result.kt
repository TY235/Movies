package com.tzeyi.movies.utilities

sealed interface Result<out T> {
    data class Success<out T>(val data: T) : Result<T>
    data class Error(val exception: Exception) : Result<Nothing>
    data object Loading : Result<Nothing>
}
