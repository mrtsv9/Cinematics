package com.example.cinematics.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cinematics.data.local.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Query("SELECT * from user where token = :token")
    suspend fun getUser(token: String): UserEntity?

    @Query("delete from user where id = 1")
    suspend fun logoutUser()

}