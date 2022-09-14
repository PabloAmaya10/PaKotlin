package com.pa.pakotlin.presentation.viewmodel

import android.app.DatePickerDialog
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pa.pakotlin.LoginState
import com.pa.pakotlin.domain.usecases.RegisterUseCase
import com.pa.pakotlin.domain.usecases.SaveUserDatabaseUseCase
import com.pa.pakotlin.presentation.model.RegisterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :
    ViewModel() {
    // private val registerUseCase = RegisterUseCase()
    private val saveUserDatabaseUseCase = SaveUserDatabaseUseCase()

    private val _date = MutableLiveData<String>()
    private val _stateRegister = MutableLiveData<LoginState>()

    val date: LiveData<String> get() = _date
    val stateRegister: LiveData<LoginState> get() = _stateRegister

    fun datePiker(context: Context) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            context,
            { _, y, m, d ->
                _date.postValue(String.format("%02d/%02d/%d", d, m, y))
            },
            year,
            month,
            day
        )
        calendar.set(Calendar.YEAR, year - 18)
        datePicker.datePicker.maxDate = calendar.timeInMillis
        calendar.set(Calendar.YEAR, year - 40) // trae el año que tu queiras
        datePicker.datePicker.minDate = calendar.timeInMillis
        datePicker.show()
    }

    fun registerUser(register: RegisterModel, context: Context) {
        viewModelScope.launch {
            _stateRegister.postValue(LoginState.Loading)
            val response = registerUseCase(register)
            // registerUserDataBase(register, context)
            if (response.status) {
                _stateRegister.postValue(LoginState.Successful)
                // registerUserDataBase(register, context)
            } else {
                _stateRegister.postValue(LoginState.Error(response.message))
            }
        }
    }
}
