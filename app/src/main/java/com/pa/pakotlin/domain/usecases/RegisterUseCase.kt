package com.pa.pakotlin.domain.usecases

import com.pa.pakotlin.data.model.toRequest
import com.pa.pakotlin.domain.repository.RegisterRepository
import com.pa.pakotlin.presentation.model.RegisterModel

class RegisterUseCase {
    private val registerRepository = RegisterRepository()

    suspend operator fun invoke(registerModel: RegisterModel) =
        registerRepository.register(registerModel.toRequest())
}
