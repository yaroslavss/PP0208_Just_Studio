package com.yara.juststudioapp.domain.model

data class User(
    val id: Int,
    val userName: String,
    val phoneNumber: String,
    val emailUser: String,
    val dateOfBirth: String,
    val photoUser: String,
    val role: String,
    val code: Int
)