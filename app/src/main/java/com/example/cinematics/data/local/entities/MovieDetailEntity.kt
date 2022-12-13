package com.example.cinematics.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_detail")
data class MovieDetailEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val original_language: String,
    val title: String,
    val backdrop_path: String,
    val release_date: String,
    val video: Boolean,
    val vote_average: Double,
    val overview: String,
    val poster_path: String,
    val userRating: Int? = 0,
)