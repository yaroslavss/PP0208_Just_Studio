package com.yara.juststudioapp.util

object Constants {
    // 127.0.0.1 on local machine -> 10.0.2.2 on emulator
    // see at https://developer.android.com/studio/run/emulator-networking
    const val REMOTE_API_BASE_URL = "http://10.0.2.2:8585"

    const val HTTP_ERROR_NOT_FOUND = "Not Found"
    const val HTTP_ERROR_UNAUTHORIZED = "Unauthorized"

    const val MSG_LOGIN_ERROR_HTTP_ERROR_NOT_FOUND = "Неправильный логин или пароль"
    const val MSG_LOGIN_ERROR_EMPTY_CREDENTIALS = "Введите email и пароль"
    const val MSG_ERROR_HTTP_EXCEPTION = "Ошибка подключения. Повторите позже"
}