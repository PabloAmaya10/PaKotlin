package com.pa.pakotlin.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pa.pakotlin.data.local.room.DbConstant.TB_NAME_USER
import com.pa.pakotlin.data.model.InfoResponse
import com.pa.pakotlin.presentation.model.RegisterModel

@Entity(tableName = TB_NAME_USER)
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "user")
    val user: String = "",
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "lastname")
    val lastName: String = "",
    @ColumnInfo(name = "secondLastName")
    val secondLastName: String = "",
    @ColumnInfo(name = "birthday")
    val birthday: String = "",
    @ColumnInfo(name = "email")
    val email: String = "",
    @ColumnInfo(name = "gender")
    val gender: String = "",
    @ColumnInfo(name = "state")
    val state: String = "",
    @ColumnInfo(name = "phone")
    val phone: String = ""

)

fun RegisterModel.toDatabase(): UserEntity = UserEntity(
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

fun InfoResponse.toDatabase(userLogin: String): UserEntity = UserEntity(
    userLogin,
    user.name,
    user.lastName,
    user.secondLastName,
    user.birthday,
    user.email,
    user.gender,
    user.state,
    user.phone
)
