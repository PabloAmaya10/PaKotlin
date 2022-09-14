package com.pa.pakotlin.presentation.model

import com.pa.pakotlin.data.local.room.entity.UserEntity

data class RegisterModel(
    var user: String = "",
    var name: String = "",
    var lastName: String = "",
    var secondLastName: String = "",
    var birthday: String = "",
    var email: String = "",
    var gender: String = "",
    var state: String = "",
    var phone: String = "",
    var password: String = "",
    var civil: String = ""
)

fun UserEntity.toModel(): RegisterModel = RegisterModel(
    user,
    name,
    lastName,
    secondLastName,
    birthday,
    email,
    gender,
    state,
    phone
)
