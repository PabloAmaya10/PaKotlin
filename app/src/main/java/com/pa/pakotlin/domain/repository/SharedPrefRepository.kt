package com.pa.pakotlin.domain.repository

import android.content.Context
import android.content.SharedPreferences
import com.pa.pakotlin.data.local.sp.SpConstant.KEY_USER
import com.pa.pakotlin.data.local.sp.SpConstant.NAME_FILE

class SharedPrefRepository {
    private lateinit var sharedPref: SharedPreferences

    // context.getSharedPreferences(NAME_FILE, Context.MODE_PRIVATE)
    fun saveData(user: String, context: Context): Boolean {
        sharedPref = context.getSharedPreferences(NAME_FILE, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(KEY_USER, user)
        editor.apply()
        return true
    }

    fun getData(context: Context): String {
        sharedPref = context.getSharedPreferences(NAME_FILE, Context.MODE_PRIVATE)
        return sharedPref.getString(KEY_USER, "").toString()
    }
}
