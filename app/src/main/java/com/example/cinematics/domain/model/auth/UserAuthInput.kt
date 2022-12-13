package com.example.photosnetwork.domain.model.auth

import com.example.cinematics.data.model.auth.UserAuthInputDto

data class UserAuthInput(
    val login: String,
    val password: String
)

fun UserAuthInput.toUserAuthInputDto(): UserAuthInputDto {
    return UserAuthInputDto(this.login, this.password)
}