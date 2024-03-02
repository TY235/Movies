package com.tzeyi.movies.data.repository

import android.app.Application
import com.tzeyi.movies.api.OmdbService
import com.tzeyi.movies.data.dto.MovieDetailsResponse
import com.tzeyi.movies.data.dto.toMovieRealmObject
import com.tzeyi.movies.data.entity.Movie
import com.tzeyi.movies.utilities.Result
import com.tzeyi.movies.utilities.isInternetAvailable
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class MovieRepository
@Inject
constructor(
    private val application: Application,
    private val service: OmdbService,
    private val dao: MovieDao,
) {
    fun getMovies(): Flow<Result<List<Movie>>> = flow {
        emit(Result.Success(dao.getAllMovies().first()))
        try {
            val apiMovies = service.getMarvelMovies().movies
            dao.insertMovies(apiMovies.map { it.toMovieRealmObject() })
            emit(Result.Success(dao.getAllMovies().first()))
        } catch (e: Exception) {
            e.printStackTrace()
            if (!isInternetAvailable(application))
                emit(Result.Error(Exception("No internet available")))
        }
    }

    suspend fun getMovieDetailsById(imdbId: String): Result<MovieDetailsResponse> =
        try {
            Result.Success(service.getMovieDetailsById(imdbId))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(
                if (isInternetAvailable(application)) e else Exception("No internet available"))
        }
}
