package com.yara.juststudioapp.domain.repository

import com.yara.juststudioapp.data.model.TokenResponse
import com.yara.juststudioapp.data.model.UserResponse
import com.yara.juststudioapp.domain.model.UserLogin
import retrofit2.Response

interface RemoteRepository {
    suspend fun signIn(userLogin: UserLogin): Response<TokenResponse>

    suspend fun getUserInfo(token: String): Response<UserResponse>
}