package com.tzeyi.movies.di

import com.tzeyi.movies.data.repository.MovieDao
import com.tzeyi.movies.data.repository.MovieDaoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DaoModule {

    @Binds abstract fun bindMovieDao(impl: MovieDaoImpl): MovieDao
}
