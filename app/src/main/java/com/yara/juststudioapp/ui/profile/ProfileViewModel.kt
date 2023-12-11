package com.yara.juststudioapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yara.juststudioapp.App
import com.yara.juststudioapp.data.mapper.UserMapper.toUser
import com.yara.juststudioapp.data.repository.RemoteRepositoryImpl
import com.yara.juststudioapp.domain.model.User
import com.yara.juststudioapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProfileViewModel : ViewModel() {

    @Inject
    lateinit var remoteRepositoryImpl: RemoteRepositoryImpl

    private val _userResult = MutableLiveData<Resource<User>>()
    val userResult: LiveData<Resource<User>> = _userResult

    init {
        App.instance.dagger.inject(this)
    }

    fun getUserInfo(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = remoteRepositoryImpl.getUserInfo(token)
                if (response.isSuccessful) {
                    _userResult.postValue(Resource.Success(response.body()!!.toUser()))
                } else {
                    _userResult.postValue(Resource.Error(response.message()))
                }
            } catch (e: HttpException) {
                // request exception
                _userResult.postValue(Resource.Error(e.toString()))
            } catch (e: IOException) {
                // no internet exception
                _userResult.postValue(Resource.Error(e.toString()))
            }
        }
    }
}