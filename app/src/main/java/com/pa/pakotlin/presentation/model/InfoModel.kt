package com.pa.pakotlin.presentation.model

import com.pa.pakotlin.data.local.room.entity.UserEntity
import com.pa.pakotlin.data.model.InfoResponse

data class UserModel(
    var user: String = "",
    var name: String = "",
    var lastName: String = "",
    var secondLastName: String = "",
    var birthday: String = "",
    var email: String = "",
    var gender: String = "",
    var state: String = "",
    var phone: String = ""
)

data class InfoModel(
    val status: Boolean,
    val userModel: UserModel,
    val message: String
)

fun InfoResponse.toModel(userLogin: String): InfoModel =
    InfoModel(
        status,
        UserModel(
            userLogin,
            user.name,
            user.lastName,
            user.secondLastName,
            user.birthday,
            user.email,
            user.gender,
            user.state,
            user.phone
        ),
        message = message ?: ""
    )

fun UserEntity.toInfoModel(): InfoModel = InfoModel(
    true,
    UserModel(
        user,
        name,
        lastName,
        secondLastName,
        birthday,
        email,
        gender,
        state,
        phone
    ),
    "Exitoso"
)
