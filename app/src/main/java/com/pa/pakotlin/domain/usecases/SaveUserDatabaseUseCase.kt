package com.pa.pakotlin.domain.usecases

import android.content.Context
import com.pa.pakotlin.LoginState
import com.pa.pakotlin.data.local.room.entity.toDatabase
import com.pa.pakotlin.domain.repository.RegisterRepository
import com.pa.pakotlin.presentation.model.RegisterModel

class SaveUserDatabaseUseCase {
    private val registerRepository = RegisterRepository()
    suspend operator fun invoke(registerModel: RegisterModel, context: Context): LoginState =
        registerRepository.registerToDataBase(registerModel.toDatabase(), context)
}
