package com.example.cinematics.di

import android.content.Context
import com.example.cinematics.data.local.AppDatabase
import com.example.cinematics.data.local.dao.MovieDetailDao
import com.example.cinematics.data.local.dao.TokenDao
import com.example.cinematics.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideMovieDao(appDatabase: AppDatabase): MovieDetailDao = appDatabase.movieDetailDao

    @Singleton
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao

    @Singleton
    @Provides
    fun provideTokenDao(appDatabase: AppDatabase): TokenDao = appDatabase.tokenDao

}