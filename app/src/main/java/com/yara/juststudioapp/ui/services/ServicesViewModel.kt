package com.yara.juststudioapp.ui.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yara.juststudioapp.model.Membership

class ServicesViewModel : ViewModel() {

    private val membershipList = listOf(
        Membership(
            item_1 = "Абонемент 1",
            item_2 = "Строка 1",
            item_3 = "Строка 2"
        ),
        Membership(
            item_1 = "Абонемент 2",
            item_2 = "Строка 1",
            item_3 = "Строка 2"
        ),
        Membership(
            item_1 = "Абонемент 3",
            item_2 = "Строка 1",
            item_3 = "Строка 2"
        )
    )
    private val _membershipListLiveData = MutableLiveData<List<Membership>>()
    val membershipListLiveData: LiveData<List<Membership>> = _membershipListLiveData

    init {
        _membershipListLiveData.postValue(membershipList)
    }
}