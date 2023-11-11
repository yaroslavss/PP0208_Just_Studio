package com.yara.juststudioapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text_address = MutableLiveData<String>().apply {
        value = "Адрес, город, улица, дом"
    }
    val text_address: LiveData<String> = _text_address

    private val _text_phone = MutableLiveData<String>().apply {
        value = "+7 (495) 788-77-50"
    }
    val text_phone: LiveData<String> = _text_phone

}