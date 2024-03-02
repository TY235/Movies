package com.tzeyi.movies.data.dto

import com.google.gson.annotations.SerializedName

data class OmdbResponse(
    @field:SerializedName("Search") val movies: List<MovieResponse>,
    @field:SerializedName("totalResults") val totalPages: Int
)
