package com.tzeyi.movies.data.dto

import com.google.gson.annotations.SerializedName
import com.tzeyi.movies.data.entity.Movie

data class MovieResponse(
    @field:SerializedName("Title") val title: String,
    @field:SerializedName("Year") val year: String,
    @field:SerializedName("imdbID") val imdbId: String,
    @field:SerializedName("Type") val type: String,
    @field:SerializedName("Poster") val poster: String,
)

fun MovieResponse.toMovieRealmObject(): Movie {
    return Movie().apply {
        this.imdbId = this@toMovieRealmObject.imdbId
        this.title = this@toMovieRealmObject.title
        this.year = this@toMovieRealmObject.year
        this.type = this@toMovieRealmObject.type
        this.poster = this@toMovieRealmObject.poster
    }
}
