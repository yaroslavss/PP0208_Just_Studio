package com.yara.juststudioapp.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("emailUser")
    val emailUser: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("dateOfBirth")
    val dateOfBirth: Any,
    @SerializedName("photoUser")
    val photoUser: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("code")
    val code: Int
)