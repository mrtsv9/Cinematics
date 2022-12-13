package com.example.cinematics.di

import android.content.Context
import androidx.room.Insert
import com.example.cinematics.data.local.AppDatabase
import com.example.cinematics.data.local.dao.MovieDetailDao
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
    fun provideUserDao(appDatabase: AppDatabase): MovieDetailDao = appDatabase.movieDetailDao

}