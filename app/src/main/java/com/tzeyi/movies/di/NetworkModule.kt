package com.tzeyi.movies.di

import com.tzeyi.movies.api.OmdbService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    
    @Provides
    @Singleton
    fun provideOmdbService(): OmdbService = OmdbService.create()
}
