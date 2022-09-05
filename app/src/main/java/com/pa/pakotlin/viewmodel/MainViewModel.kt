package com.pa.pakotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pa.pakotlin.model.Constants.PASSWORD
import com.pa.pakotlin.model.Constants.USER
import com.pa.pakotlin.model.CredentialsModel
import com.pa.pakotlin.model.ErrorLogin

class MainViewModel : ViewModel() {

    private val _errorLogin = MutableLiveData<ErrorLogin>()
    private val _credentials = MutableLiveData<CredentialsModel>()

    val errorLogin: LiveData<ErrorLogin> get() = _errorLogin
    val credentials: LiveData<CredentialsModel> get() = _credentials

    fun login(credentials: CredentialsModel) {
        when {
            credentials.user.isBlank() || credentials.user.isEmpty() ->
                _errorLogin.postValue(
                    ErrorLogin.USER_EMPTY
                )

            credentials.user != USER ->
                _errorLogin.postValue(
                    ErrorLogin.USER_ERROR
                )
            credentials.password.isBlank() || credentials.password.isEmpty() ->
                _errorLogin.postValue(
                    ErrorLogin.PASSWORD_EMPTY
                )
            credentials.password != PASSWORD ->
                _errorLogin.postValue(
                    ErrorLogin.PASSWORD_ERROR
                )
            else -> {
                _credentials.postValue(credentials)
            }
        }
    }
}
