package com.pa.pakotlin.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pa.pakotlin.InfoState
import com.pa.pakotlin.domain.usecases.GetUserDatabaseUseCase
import com.pa.pakotlin.domain.usecases.SaveDataSpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val saveDataSpUseCase: SaveDataSpUseCase,
    private val getUserDatabaseUseCase: GetUserDatabaseUseCase
) :
    ViewModel() {
    // private val saveDataSpUseCase = SaveDataSpUseCase()

    // private val getUserDataApiUseCase = GetUserDataApiUseCase()
    // private val getUserDatabaseUseCase = GetUserDatabaseUseCase()

    private val _stateSp = MutableLiveData<Boolean>()
    private val _userInfo = MutableLiveData<InfoState>()

    val stateSp: LiveData<Boolean> get() = _stateSp
    val userInfo: LiveData<InfoState> get() = _userInfo

    fun getUserInfo(user: String, context: Context) {
        viewModelScope.launch {
            _userInfo.postValue(InfoState.Loading)
            val response = getUserDatabaseUseCase(user, context)
            if (response.status) {
                _userInfo.postValue(InfoState.Successful(response.userModel))
            } else {
                _userInfo.postValue(InfoState.Error(response.message))
            }
        }
    }

    fun saveSharedPref(user: String, context: Context) {
        viewModelScope.launch {
            val response = saveDataSpUseCase(user, context)
            if (response) {
                _stateSp.postValue(true)
            }
        }
    }
}
