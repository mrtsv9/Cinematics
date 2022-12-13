package com.example.cinematics.domain.repository.auth

import com.example.cinematics.domain.model.auth.UserAuthItem
import com.example.photosnetwork.domain.model.auth.UserAuthInput

interface RegisterRepository {

    suspend fun registerUser(userAuthInput: UserAuthInput): UserAuthItem?

}