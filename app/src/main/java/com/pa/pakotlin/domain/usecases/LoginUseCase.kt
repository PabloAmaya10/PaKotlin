package com.pa.pakotlin.domain.usecases

import com.pa.pakotlin.data.model.LoginRequest
import com.pa.pakotlin.domain.repository.LoginRepository

class LoginUseCase(private val loginRepository: LoginRepository) {
    // private val loginRepository: LoginRepository by inject()
    // private val loginRepository = LoginRepository()
    suspend operator fun invoke(loginRequest: LoginRequest) = loginRepository.login(loginRequest)
}
