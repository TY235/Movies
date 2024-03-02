package com.tzeyi.movies.data.dto

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @field:SerializedName("Title") val title: String = "",
    @field:SerializedName("Year") val year: String = "",
    @field:SerializedName("Rated") val rated: String = "",
    @field:SerializedName("Released") val released: String = "",
    @field:SerializedName("Runtime") val runtime: String = "",
    @field:SerializedName("Genre") val genre: String = "",
    @field:SerializedName("Director") val director: String = "",
    @field:SerializedName("Writer") val writer: String = "",
    @field:SerializedName("Actors") val actors: String = "",
    @field:SerializedName("Plot") val plot: String = "",
    @field:SerializedName("Language") val language: String = "",
    @field:SerializedName("Country") val country: String = "",
    @field:SerializedName("Awards") val awards: String = "",
    @field:SerializedName("Poster") val poster: String = "",
    @field:SerializedName("Ratings") val ratings: List<Rating> = emptyList(),
    @field:SerializedName("Metascore") val metascore: String = "",
    @field:SerializedName("imdbRating") val imdbRating: String = "0",
    @field:SerializedName("imdbVotes") val imdbVotes: String = "",
    @field:SerializedName("imdbID") val imdbId: String = "",
    @field:SerializedName("Type") val type: String = "",
    @field:SerializedName("DVD") val dvd: String = "",
    @field:SerializedName("BoxOffice") val boxOffice: String = "",
    @field:SerializedName("Production") val production: String = "",
    @field:SerializedName("Website") val website: String = "",
    @field:SerializedName("Response") val response: String = "",
)

data class Rating(
    @field:SerializedName("Source") val source: String = "",
    @field:SerializedName("Value") val value: String = "",
)
