package com.example.cinematics.data.local

import android.content.Context
import androidx.room.*
import com.example.cinematics.data.local.dao.MovieDetailDao
import com.example.cinematics.data.local.dao.TokenDao
import com.example.cinematics.data.local.dao.UserDao
import com.example.cinematics.data.local.entities.MovieDetailEntity
import com.example.cinematics.data.local.entities.TokenEntity
import com.example.cinematics.data.local.entities.UserEntity

@Database(
    entities = [
        MovieDetailEntity::class,
        UserEntity::class,
        TokenEntity::class
    ],
    exportSchema = false,
    version = 3
)
abstract class AppDatabase : RoomDatabase() {

    abstract val movieDetailDao: MovieDetailDao
    abstract val userDao: UserDao
    abstract val tokenDao: TokenDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movies"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }

}