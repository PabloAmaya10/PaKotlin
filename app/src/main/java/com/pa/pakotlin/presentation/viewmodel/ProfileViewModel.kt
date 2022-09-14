package com.pa.pakotlin.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pa.pakotlin.domain.usecases.GetUserDatabaseUseCase
import com.pa.pakotlin.presentation.model.RegisterModel

class ProfileViewModel : ViewModel() {
    private val _profileData = MutableLiveData<RegisterModel>()

    private val getUserDatabaseUseCase = GetUserDatabaseUseCase()

    val profileData: LiveData<RegisterModel> get() = _profileData

    /*fun getUserDatabase(user: String, context: Context) {
        viewModelScope.launch {
            val response = getUserDatabaseUseCase(user, context)
            _profileData.postValue(response)
        }
    }*/
}
