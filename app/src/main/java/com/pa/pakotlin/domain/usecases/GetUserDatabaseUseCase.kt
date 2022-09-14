package com.pa.pakotlin.domain.usecases

import android.content.Context
import com.pa.pakotlin.domain.repository.GetDatabaseRepository
import javax.inject.Inject

class GetUserDatabaseUseCase @Inject constructor(private val getDatabaseRepository: GetDatabaseRepository) {
    // private val getDatabaseRepository = GetDatabaseRepository()
    suspend operator fun invoke(user: String, context: Context) =
        getDatabaseRepository.getInfoFromDataBase(user, context)
}
