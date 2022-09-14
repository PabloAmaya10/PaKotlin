package com.pa.pakotlin.data.local.room

import android.content.Context
import androidx.room.Room

class DataBase(private val context: Context) {

    private var dataBase: DB? = null

    private fun createDataBase() {
        dataBase = Room.databaseBuilder(context, DB::class.java, DbConstant.DB_NAME)
            .allowMainThreadQueries().build()
        if (!dataBase!!.isOpen) {
            val pathDb = dataBase!!.openHelper.writableDatabase
            println("DB PATH $pathDb")
        }
    }

    fun getDataBase(): DB {
        if (dataBase == null) {
            createDataBase()
        }
        return dataBase!!
    }
}
