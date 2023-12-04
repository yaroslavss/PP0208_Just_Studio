package com.yara.juststudioapp.ui.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yara.juststudioapp.domain.model.Membership

class ServicesViewModel : ViewModel() {

    private val membershipList = listOf(
        Membership(
            title = "Абонемент 1",
            item_1 = "Строка 1",
            item_2 = "Строка 2",
            item_3 = "Строка 3",
            price = 1000
        ),
        Membership(
            title = "Абонемент 2",
            item_1 = "Строка 1",
            item_2 = "Строка 2",
            item_3 = "Строка 3",
            price = 1000
        ),
        Membership(
            title = "Абонемент 3",
            item_1 = "Строка 1",
            item_2 = "Строка 2",
            item_3 = "Строка 3",
            price = 1000
        ),
        Membership(
            title = "Абонемент 4",
            item_1 = "Строка 1",
            item_2 = "Строка 2",
            item_3 = "Строка 3",
            price = 1000
        ),
        Membership(
            title = "Абонемент 5",
            item_1 = "Строка 1",
            item_2 = "Строка 2",
            item_3 = "Строка 3",
            price = 1000
        ),
        Membership(
            title = "Абонемент 6",
            item_1 = "Строка 1",
            item_2 = "Строка 2",
            item_3 = "Строка 3",
            price = 1000
        )
    )
    private val _membershipListLiveData = MutableLiveData<List<Membership>>()
    val membershipListLiveData: LiveData<List<Membership>> = _membershipListLiveData

    init {
        _membershipListLiveData.postValue(membershipList)
    }
}