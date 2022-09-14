package com.pa.pakotlin.domain.usecases

import android.content.Context
import com.pa.pakotlin.domain.repository.InfoRepository

class GetUserDataApiUseCase {
    private val infoRepository = InfoRepository()
    suspend operator fun invoke(user: String, context: Context) =
        infoRepository.getInfoUser(user, context)
}
