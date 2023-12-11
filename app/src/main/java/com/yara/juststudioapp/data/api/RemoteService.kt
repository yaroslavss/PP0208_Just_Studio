package com.yara.juststudioapp.data.api

import com.yara.juststudioapp.data.model.TokenResponse
import com.yara.juststudioapp.data.model.UserResponse
import com.yara.juststudioapp.domain.model.UserLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface RemoteService {
    @Headers("Content-Type: application/json")
    @POST("/login")
    suspend fun signIn(
        @Body userLogin: UserLogin,
    ): Response<TokenResponse>

    @GET("/basicSettings")
    suspend fun getUserInfo(
        @Header("Authorization") token: String,
    ): Response<UserResponse>
}