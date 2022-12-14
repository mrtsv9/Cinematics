package com.example.cinematics.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cinematics.data.local.entities.TokenEntity

@Dao
interface TokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(tokenEntity: TokenEntity)

    @Query("select * from token where id = 1")
    suspend fun getToken(): TokenEntity?

    @Query("delete from token where id = 1")
    suspend fun deleteToken()

}