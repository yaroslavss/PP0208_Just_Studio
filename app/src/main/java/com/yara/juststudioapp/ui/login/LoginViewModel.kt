package com.yara.juststudioapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yara.juststudioapp.App
import com.yara.juststudioapp.data.model.TokenResponse
import com.yara.juststudioapp.data.repository.RemoteRepositoryImpl
import com.yara.juststudioapp.domain.model.UserLogin
import com.yara.juststudioapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginViewModel : ViewModel() {

    @Inject
    lateinit var remoteRepositoryImpl: RemoteRepositoryImpl

    private val _loginResult = MutableLiveData<Resource<TokenResponse>>()
    val loginResult: LiveData<Resource<TokenResponse>> = _loginResult

    init {
        App.instance.dagger.inject(this)
    }

    fun signIn(userLogin: UserLogin) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = remoteRepositoryImpl.signIn(userLogin)
                if (response.isSuccessful) {
                    _loginResult.postValue(Resource.Success(response.body()!!))

                } else {
                    _loginResult.postValue(Resource.Error(response.message()))
                }
            } catch (e: HttpException) {
                // request exception
                _loginResult.postValue(Resource.Error(e.toString()))
            } catch (e: IOException) {
                // no internet exception
                _loginResult.postValue(Resource.Error(e.toString()))
            }
        }
    }
}