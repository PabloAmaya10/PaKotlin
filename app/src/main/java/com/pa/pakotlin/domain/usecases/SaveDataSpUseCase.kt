package com.pa.pakotlin.domain.usecases

import android.content.Context
import com.pa.pakotlin.domain.repository.SharedPrefRepository
import javax.inject.Inject

class SaveDataSpUseCase @Inject constructor(private val sharedPrefRepository: SharedPrefRepository) {

    // /private val sharedPrefRepository = SharedPrefRepository()
    suspend operator fun invoke(user: String, context: Context) =
        sharedPrefRepository.saveData(user, context)
}
