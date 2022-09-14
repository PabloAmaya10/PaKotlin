package com.pa.pakotlin.domain.repository

import android.content.Context
import com.pa.pakotlin.data.local.room.DataBase
import com.pa.pakotlin.data.local.room.entity.toDatabase
import com.pa.pakotlin.data.network.Path.BASE_URL
import com.pa.pakotlin.data.network.RetrofitClient
import com.pa.pakotlin.presentation.model.InfoModel
import com.pa.pakotlin.presentation.model.UserModel
import com.pa.pakotlin.presentation.model.toInfoModel
import com.pa.pakotlin.presentation.model.toModel

class InfoRepository {
    private val wifi = true
    suspend fun getInfoUser(userName: String, context: Context): InfoModel {
        val dataBase = DataBase(context).getDataBase()

        return if (wifi) {
            val infoUser = RetrofitClient.api(BASE_URL).getInfo(userName)
            dataBase.daoUser().insertOrUpdate(infoUser.toDatabase(userName))
            infoUser.toModel(userName)
        } else {
            val user = dataBase.daoUser().getUser(userName)
            user?.toInfoModel()
                ?: InfoModel(
                    false,
                    UserModel(),
                    "No hay internet y el usuario no existe en base de datos"
                )
        }
    }
}
