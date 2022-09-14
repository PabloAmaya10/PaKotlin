package com.pa.pakotlin.domain.repository

import android.content.Context
import com.pa.pakotlin.LoginState
import com.pa.pakotlin.data.local.room.DataBase
import com.pa.pakotlin.data.local.room.entity.UserEntity
import com.pa.pakotlin.data.model.LoginResponse
import com.pa.pakotlin.data.model.RegisterRequest
import com.pa.pakotlin.data.network.Path.BASE_URL
import com.pa.pakotlin.data.network.RetrofitClient

class RegisterRepository {
    var wifi = true

    suspend fun register(registerRequest: RegisterRequest): LoginResponse {
        return if (wifi) {
            RetrofitClient.api(BASE_URL).postRegister(registerRequest)
            // LoginResponse(true, "Success from api")
        } else {
            LoginResponse(false, "No tiene internet")
        }
    }

    suspend fun registerToDataBase(registerDataBase: UserEntity, context: Context): LoginState {
        val dataBase = DataBase(context).getDataBase()
        // dataBase.daoUser().deleteUser()
        return try {
            dataBase.daoUser().insert(registerDataBase)

            // val user = dataBase.daoUser().getUser()
            // println(user)
            LoginState.Successful
        } catch (e: Exception) {
            LoginState.Error(e.message.toString())
        }
    }
}
