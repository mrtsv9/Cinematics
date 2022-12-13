package com.example.cinematics.di

import com.example.cinematics.data.datasource.remote.ApiService
import com.example.cinematics.data.local.dao.MovieDetailDao
import com.example.cinematics.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        apiService: ApiService,
        dao: MovieDetailDao,
    ): MovieRepository {
        return MovieRepository(apiService, dao)
    }

}