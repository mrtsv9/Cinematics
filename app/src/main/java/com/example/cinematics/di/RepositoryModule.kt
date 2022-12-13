package com.example.cinematics.di

import com.example.cinematics.data.datasource.remote.ApiService
import com.example.cinematics.data.local.dao.MovieDetailDao
import com.example.cinematics.data.local.dao.UserDao
import com.example.cinematics.data.repository.MovieRepository
import com.example.cinematics.data.repository.auth.LoginRepositoryImpl
import com.example.cinematics.data.repository.auth.RegisterRepositoryImpl
import com.example.cinematics.domain.repository.auth.LoginRepository
import com.example.cinematics.domain.repository.auth.RegisterRepository
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
        userDao: UserDao
    ): MovieRepository {
        return MovieRepository(apiService, dao, userDao)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(): LoginRepository = LoginRepositoryImpl()

    @Provides
    @Singleton
    fun provideRegisterRepository(): RegisterRepository = RegisterRepositoryImpl()

}