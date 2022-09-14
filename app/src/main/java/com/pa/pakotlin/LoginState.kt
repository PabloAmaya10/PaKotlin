package com.pa.pakotlin

enum class TypeError {
    USER,
    PASSWORD,
    INTERNET,
    SERVER,
}

data class CustomError(val message: String, val code: Int, val type: TypeError)

sealed class LoginState {
    object Loading : LoginState()
    object Successful : LoginState()
    data class Error(val message: String) : LoginState()
}
