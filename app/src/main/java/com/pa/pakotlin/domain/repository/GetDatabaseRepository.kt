package com.pa.pakotlin.domain.repository

import android.content.Context
import com.pa.pakotlin.data.local.room.DataBase
import com.pa.pakotlin.presentation.model.*
import javax.inject.Inject

class GetDatabaseRepository @Inject constructor() {

    suspend fun getInfoFromDataBase(userLogin: String, context: Context): InfoModel {
        val dataBase = DataBase(context).getDataBase()
        val user = dataBase.daoUser().getUser(userLogin)
        return user?.toInfoModel() ?: InfoModel(false, UserModel(), "error en base de datos ")
    }
    /* suspend fun getUserDataBase(user: String, context: Context): RegisterModel {
         val dataBase = DataBase(context).getDataBase()
         // dataBase.daoUser().deleteUser()
         return try {
             val user = dataBase.daoUser().getUser(user)
             user?.toModel() ?: RegisterModel()
         } catch (e: Exception) {
             RegisterModel()
         }
     }*/
}
