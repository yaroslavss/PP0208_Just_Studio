package com.yara.juststudioapp.domain.model

data class Notification(
    val title: String,
    val dt: String,
    var isUnread: Boolean
)