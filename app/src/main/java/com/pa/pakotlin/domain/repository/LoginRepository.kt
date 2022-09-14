package com.pa.pakotlin.domain.repository

import com.pa.pakotlin.data.model.LoginRequest
import com.pa.pakotlin.data.model.LoginResponse
import com.pa.pakotlin.data.network.Path.BASE_URL
import com.pa.pakotlin.data.network.RetrofitClient
import javax.inject.Inject

class LoginRepository @Inject constructor() {

    var wifi = true

    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return if (wifi) {
            RetrofitClient.api(BASE_URL).postLogin(loginRequest)
            // LoginResponse(true, "Success from api")
        } else {
            LoginResponse(false, "No tiene internet")
        }
    }
}
