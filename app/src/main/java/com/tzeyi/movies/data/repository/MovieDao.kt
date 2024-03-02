package com.tzeyi.movies.data.repository

import com.tzeyi.movies.data.entity.Movie
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface MovieDao {

    fun getAllMovies(): Flow<List<Movie>>

    suspend fun insertMovie(movie: Movie)

    suspend fun insertMovies(movies: List<Movie>)

    suspend fun updateMovie(movie: Movie)

    suspend fun deleteMovie(imdbId: String)

    suspend fun deleteAllMovies()
}

class MovieDaoImpl @Inject constructor(private val realm: Realm) : MovieDao {

    override fun getAllMovies(): Flow<List<Movie>> =
        realm.query(Movie::class).asFlow().map { it.list.toList() }

    override suspend fun insertMovie(movie: Movie) {
        realm.write { copyToRealm(movie, UpdatePolicy.ALL) }
    }

    override suspend fun insertMovies(movies: List<Movie>) {
        realm.write { movies.map { copyToRealm(it, UpdatePolicy.ALL) } }
    }

    override suspend fun updateMovie(movie: Movie) =
        realm.write {
            val updatedMovie = query<Movie>("imdbId == $0", movie.imdbId).find().first()
            updatedMovie.title = movie.title
            updatedMovie.year = movie.year
            updatedMovie.type = movie.type
            updatedMovie.poster = movie.poster
        }

    override suspend fun deleteMovie(imdbId: String) =
        realm.write {
            val movieToDelete = query<Movie>("imdbId == $0", imdbId).find().first()
            delete(movieToDelete)
        }

    override suspend fun deleteAllMovies() =
        realm.write {
            val allMovies = query<Movie>().find()
            delete(allMovies)
        }
}
