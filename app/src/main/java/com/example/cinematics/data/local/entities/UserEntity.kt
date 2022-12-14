package com.example.cinematics.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cinematics.domain.model.auth.UserAuthItem

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val login: String?,
    val token: String?,
)

fun UserEntity.toUserAuthItem(): UserAuthItem {
    return UserAuthItem(id, login, token)
}