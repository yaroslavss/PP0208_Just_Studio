package com.yara.juststudioapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserLogin(
    val emailUser: String,
    val password: String
) : Parcelable