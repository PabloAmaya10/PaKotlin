package com.pa.pakotlin.data.model

import com.google.gson.annotations.SerializedName
import com.pa.pakotlin.presentation.model.RegisterModel

data class RegisterRequest(
    @SerializedName("user") val user: String,
    @SerializedName("name") val name: String,
    @SerializedName("lastname") val lastName: String,
    @SerializedName("secondLastName") val secondLastName: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("email") val email: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("state") val state: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("password") val password: String,
    @SerializedName("civil") val civil: String
)

fun RegisterModel.toRequest(): RegisterRequest = RegisterRequest(
    user,
    name,
    lastName,
    secondLastName,
    birthday,
    email,
    gender,
    state,
    phone,
    password,
    "soltero"
)
