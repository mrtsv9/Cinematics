package com.example.cinematics.domain.use_case.auth

import com.example.cinematics.data.local.dao.TokenDao
import com.example.cinematics.data.local.dao.UserDao
import com.example.cinematics.data.local.entities.TokenEntity
import com.example.cinematics.data.local.entities.UserEntity
import com.example.cinematics.domain.model.auth.UserAuthItem
import com.example.photosnetwork.domain.model.auth.UserAuthInput
import com.example.cinematics.domain.repository.auth.RegisterRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: RegisterRepository,
    private val dao: UserDao,
    private val tokenDao: TokenDao,
) {

    suspend operator fun invoke(userAuthInput: UserAuthInput): UserAuthItem? {
        val userAuthData = repository.registerUser(userAuthInput) ?: return null
        dao.insertUser(UserEntity(1, userAuthData.login, userAuthData.token))
        tokenDao.insertToken(TokenEntity(1, userAuthData.login.toString()))
        return userAuthData
    }

}