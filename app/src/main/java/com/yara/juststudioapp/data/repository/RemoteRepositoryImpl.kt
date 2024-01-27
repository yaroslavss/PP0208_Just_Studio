package com.yara.juststudioapp.data.repository

import com.yara.juststudioapp.data.api.RemoteService
import com.yara.juststudioapp.data.model.RegisterResponse
import com.yara.juststudioapp.data.model.TokenResponse
import com.yara.juststudioapp.data.model.UserResponse
import com.yara.juststudioapp.domain.model.UserLogin
import com.yara.juststudioapp.domain.repository.RemoteRepository
import retrofit2.Response
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val remoteService: RemoteService) :
    RemoteRepository {

    override suspend fun signIn(userLogin: UserLogin): Response<TokenResponse> {
        return remoteService.signIn(userLogin)
    }

    override suspend fun getUserInfo(token: String): Response<UserResponse> {
        return remoteService.getUserInfo(token)
    }

    override suspend fun register(userLogin: UserLogin): Response<RegisterResponse> {
        return remoteService.register(userLogin)
    }
}