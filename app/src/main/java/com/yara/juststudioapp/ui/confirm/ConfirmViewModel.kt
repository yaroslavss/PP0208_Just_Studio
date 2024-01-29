package com.yara.juststudioapp.ui.confirm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yara.juststudioapp.App
import com.yara.juststudioapp.data.model.RegisterResponse
import com.yara.juststudioapp.data.repository.RemoteRepositoryImpl
import com.yara.juststudioapp.domain.model.UserLoginWithCode
import com.yara.juststudioapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ConfirmViewModel : ViewModel() {

    @Inject
    lateinit var remoteRepositoryImpl: RemoteRepositoryImpl

    private val _confirmResult = MutableLiveData<Resource<RegisterResponse>>()
    val confirmResult: LiveData<Resource<RegisterResponse>> = _confirmResult

    init {
        App.instance.dagger.inject(this)
    }

    fun confirm(userLoginWithCode: UserLoginWithCode) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = remoteRepositoryImpl.confirm(userLoginWithCode)
                if (response.isSuccessful) {
                    _confirmResult.postValue(Resource.Success(response.body()!!))

                } else {
                    _confirmResult.postValue(Resource.Error(response.message()))
                }
            } catch (e: HttpException) {
                // request exception
                _confirmResult.postValue(Resource.Error(e.toString()))
            } catch (e: IOException) {
                // no internet exception
                _confirmResult.postValue(Resource.Error(e.toString()))
            }
        }
    }
}