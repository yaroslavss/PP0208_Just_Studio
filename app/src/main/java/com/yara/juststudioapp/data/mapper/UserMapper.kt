package com.yara.juststudioapp.data.mapper

import com.yara.juststudioapp.data.model.UserResponse
import com.yara.juststudioapp.domain.model.User

object UserMapper {
    fun UserResponse.toUser() = User(
        id = this.id,
        userName = this.userName,
        phoneNumber = this.phoneNumber,
        emailUser = this.emailUser,
        dateOfBirth = if (this.dateOfBirth != null) this.dateOfBirth.toString() else "",
        photoUser = this.photoUser,
        role = this.role,
        code = this.code
    )
}