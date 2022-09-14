package com.pa.pakotlin.domain.usecases

import android.content.Context
import com.pa.pakotlin.domain.repository.GetDatabaseRepository

class GetUserDatabaseUseCase(private val getDatabaseRepository: GetDatabaseRepository) {
    // private val getDatabaseRepository = GetDatabaseRepository()
    suspend operator fun invoke(user: String, context: Context) =
        getDatabaseRepository.getInfoFromDataBase(user, context)
}
