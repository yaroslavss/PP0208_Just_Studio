package com.yara.juststudioapp.domain.model

data class UserLoginWithCode(
    val emailUser: String,
    val password: String,
    val code: String
)