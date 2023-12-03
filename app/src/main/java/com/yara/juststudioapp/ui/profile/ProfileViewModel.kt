package com.yara.juststudioapp.ui.profile

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

class ProfileViewModel : ViewModel() {

    @Inject
    lateinit var remoteRepositoryImpl: RemoteRepositoryImpl

    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
    val text: LiveData<String> = _text
    private val _token = MutableLiveData<Resource<TokenResponse>>()
    val token: LiveData<Resource<TokenResponse>> = _token

    init {
        App.instance.dagger.inject(this)
        signIn(UserLogin("example@ups.ru", "Aa1234566_"))
    }

    fun signIn(userLogin: UserLogin) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                val response = remoteRepositoryImpl.signIn(userLogin)
                if (response.isSuccessful) {
                    Resource.Success(response.body()!!)
                } else {
                    Resource.Error(response.message())
                }
            } catch (e: HttpException) {
                // request exception
                Resource.Error(e.toString())
            } catch (e: IOException) {
                // no internet exception
                Resource.Error(e.toString())
            }
            _token.postValue(result)
        }
    }
}