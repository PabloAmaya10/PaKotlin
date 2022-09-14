package com.pa.pakotlin.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pa.pakotlin.domain.usecases.GetDataSpUseCase
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _user = MutableLiveData<String>()

    private val getDataSpUseCase = GetDataSpUseCase()

    val user: LiveData<String> get() = _user

    fun gatUserSharedPref(context: Context) {
        viewModelScope.launch {
            val response = getDataSpUseCase(context)
            _user.postValue(response)
        }
    }
}
