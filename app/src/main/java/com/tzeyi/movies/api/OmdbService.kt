package com.tzeyi.movies.api

import com.tzeyi.movies.BuildConfig
import com.tzeyi.movies.data.dto.MovieDetailsResponse
import com.tzeyi.movies.data.dto.OmdbResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbService {

    @GET("/")
    suspend fun getMarvelMovies(
        @Query("s") title: String = "Marvel",
        @Query("type") type: String = "movie",
    ): OmdbResponse

    @GET("/") suspend fun getMovieDetailsById(@Query("i") imdbId: String): MovieDetailsResponse

    companion object {
        private const val BASE_URL = "https://www.omdbapi.com/"

        fun create(): OmdbService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val apiKey = Interceptor { chain ->
                var request = chain.request()
                val url =
                    request.url
                        .newBuilder()
                        .addQueryParameter("apikey", BuildConfig.OMDB_API_KEY)
                        .build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }

            val client =
                OkHttpClient.Builder().addInterceptor(logger).addInterceptor(apiKey).build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OmdbService::class.java)
        }
    }
}
