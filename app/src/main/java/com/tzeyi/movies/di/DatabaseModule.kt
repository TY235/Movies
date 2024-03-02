package com.tzeyi.movies.di

import com.tzeyi.movies.data.entity.Movie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    
    @Provides
    @Singleton
    fun provideRealm(): Realm {
        val config = RealmConfiguration.create(schema = setOf(Movie::class))
        return Realm.open(config)
    }
}
