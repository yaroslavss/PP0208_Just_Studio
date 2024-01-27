package com.yara.juststudioapp.ui.profile.inner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yara.juststudioapp.domain.model.Notification

class ProfileNotificationsViewModel : ViewModel() {

    private val notificationList = listOf(
        Notification(
            title = "Текст нового уведомления",
            dt = "19:20",
            isUnread = true
        ),
        Notification(
            title = "Текст нового уведомления",
            dt = "10:20",
            isUnread = true
        ),
        Notification(
            title = "Текст уведомления",
            dt = "9:20",
            isUnread = false
        ),
    )
    private val _notificationListLiveData = MutableLiveData<List<Notification>>()
    val notificationListLiveData: LiveData<List<Notification>> = _notificationListLiveData

    init {
        _notificationListLiveData.postValue(notificationList)
    }
}