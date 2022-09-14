package com.pa.pakotlin

import com.pa.pakotlin.presentation.model.UserModel

sealed class InfoState {
    object Loading : InfoState()
    data class Successful(val userModel: UserModel) : InfoState()
    data class Error(val message: String) : InfoState()
}
