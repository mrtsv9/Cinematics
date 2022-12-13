package com.example.cinematics.data.repository.auth

import com.example.cinematics.data.datasource.remote.ApiURL
import com.example.cinematics.data.datasource.remote.auth.LoginApi
import com.example.cinematics.data.datasource.remote.auth.RegisterApi
import com.example.cinematics.data.model.auth.toUserAuthItem
import com.example.cinematics.domain.model.auth.UserAuthItem
import com.example.cinematics.domain.repository.auth.RegisterRepository
import com.example.photosnetwork.domain.model.auth.UserAuthInput
import com.example.photosnetwork.domain.model.auth.toUserAuthInputDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterRepositoryImpl :
    RegisterRepository {

    override suspend fun registerUser(userAuthInput: UserAuthInput): UserAuthItem? {
        val retrofit = Retrofit.Builder().baseUrl(ApiURL.AUTH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(RegisterApi::class.java)
        val response = api.userSignUp(userAuthInput.toUserAuthInputDto())
        return when (response.code()) {
            200 -> response.body()?.toUserAuthItem()
            400 -> null
            else -> null
        }
    }

}