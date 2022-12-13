package com.example.cinematics.data.local.dao

import android.icu.text.CaseMap.Title
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cinematics.data.local.entities.MovieDetailEntity

@androidx.room.Dao
interface MovieDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(movie: MovieDetailEntity)

    @Query("select * from movie_detail")
    suspend fun getAll(): List<MovieDetailEntity>

    @Query("select * from movie_detail where title = :title")
    suspend fun isPresentByTitle(title: String): MovieDetailEntity?

    @Query("delete from movie_detail where title = :title")
    suspend fun deleteItem(title: String)
}