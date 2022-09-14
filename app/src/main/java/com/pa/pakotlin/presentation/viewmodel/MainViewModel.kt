package com.pa.pakotlin.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pa.pakotlin.LoginState
import com.pa.pakotlin.data.model.LoginRequest
import com.pa.pakotlin.domain.usecases.GetUserDataApiUseCase
import com.pa.pakotlin.domain.usecases.LoginUseCase
import com.pa.pakotlin.model.CredentialsModel
import com.pa.pakotlin.model.ErrorLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getUserDataApiUseCase: GetUserDataApiUseCase
) : ViewModel() {

    // private val loginUseCase = LoginUseCase()
    // private val getUserDataApiUseCase = GetUserDataApiUseCase()

    private val _stateLogin = MutableLiveData<LoginState>()
    private val _errorLogin = MutableLiveData<ErrorLogin>()
    private val _credentials = MutableLiveData<CredentialsModel>()

    val errorLogin: LiveData<ErrorLogin> get() = _errorLogin
    val credentials: LiveData<CredentialsModel> get() = _credentials
    val stateLogin: LiveData<LoginState> get() = _stateLogin

    fun login(loginRequest: LoginRequest, context: Context) {
        println("Usuario:${loginRequest.user} Password:${loginRequest.pass}")

        viewModelScope.launch {
            _stateLogin.postValue(LoginState.Loading)
            // _stateLogin.postValue(LoginState.Successful)
            val response = loginUseCase(loginRequest)
            // Thread.sleep(2000)
            if (response.status) {
                val user = getUserDataApiUseCase(loginRequest.user, context)
                if (user.status) {
                    _stateLogin.postValue(LoginState.Successful)
                } else {
                    LoginState.Error(user.message)
                }
            } else {
                _stateLogin.postValue(LoginState.Error(response.message))
            }
        }
    }

/*fun login(credentials: CredentialsModel) {
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
}*/
}
