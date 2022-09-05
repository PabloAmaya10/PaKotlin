package com.pa.pakotlin.model

enum class ErrorLogin(val error: String) {
    USER_ERROR("El usuario es incorrecto"),
    USER_EMPTY("El campo usiario es obligatorio"),
    PASSWORD_ERROR("El usuario es incorrecto"),
    PASSWORD_EMPTY("El campo usiario es obligatorio")
}
