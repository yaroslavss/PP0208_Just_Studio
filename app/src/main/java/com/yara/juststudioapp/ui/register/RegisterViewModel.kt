package com.yara.juststudioapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yara.juststudioapp.App
import com.yara.juststudioapp.data.model.RegisterResponse
import com.yara.juststudioapp.data.repository.RemoteRepositoryImpl
import com.yara.juststudioapp.domain.model.UserLogin
import com.yara.juststudioapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RegisterViewModel : ViewModel() {

    @Inject
    lateinit var remoteRepositoryImpl: RemoteRepositoryImpl

    private val _registerResult = MutableLiveData<Resource<RegisterResponse>>()
    val registerResult: LiveData<Resource<RegisterResponse>> = _registerResult

    init {
        App.instance.dagger.inject(this)
    }

    fun register(userLogin: UserLogin) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = remoteRepositoryImpl.register(userLogin)
                if (response.isSuccessful) {
                    _registerResult.postValue(Resource.Success(response.body()!!))

                } else {
                    _registerResult.postValue(Resource.Error(response.message()))
                }
            } catch (e: HttpException) {
                // request exception
                _registerResult.postValue(Resource.Error(e.toString()))
            } catch (e: IOException) {
                // no internet exception
                _registerResult.postValue(Resource.Error(e.toString()))
            }
        }
    }
}