package com.pa.pakotlin.domain.usecases

import android.content.Context
import com.pa.pakotlin.domain.repository.SharedPrefRepository

class SaveDataSpUseCase(private val sharedPrefRepository: SharedPrefRepository) {

    // private val sharedPrefRepository = SharedPrefRepository()
    suspend operator fun invoke(user: String, context: Context) =
        sharedPrefRepository.saveData(user, context)
}
