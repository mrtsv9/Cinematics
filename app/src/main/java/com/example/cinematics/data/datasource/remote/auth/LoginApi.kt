package com.example.cinematics.data.datasource.remote.auth

import com.example.cinematics.data.model.auth.UserAuthInputDto
import com.example.cinematics.data.model.auth.UserAuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("api/account/signin")
    suspend fun userSignIn(@Body body: UserAuthInputDto): Response<UserAuthResponse>

}