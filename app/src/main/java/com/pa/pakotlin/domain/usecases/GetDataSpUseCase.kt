package com.pa.pakotlin.domain.usecases

import android.content.Context
import com.pa.pakotlin.domain.repository.SharedPrefRepository

class GetDataSpUseCase {

    private val sharedPrefRepository = SharedPrefRepository()
    suspend operator fun invoke(context: Context) =
        sharedPrefRepository.getData(context)
}
