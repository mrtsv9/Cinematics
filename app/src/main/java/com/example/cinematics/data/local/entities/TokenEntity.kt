package com.example.cinematics.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.IDN

@Entity(tableName = "token")
data class TokenEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1,
    val token: String?,
)
