package com.pa.pakotlin.data.network

import com.pa.pakotlin.data.model.*
import com.pa.pakotlin.data.network.Path.GET_INFO
import com.pa.pakotlin.data.network.Path.GET_POKEMON
import com.pa.pakotlin.data.network.Path.POST_LOGIN
import com.pa.pakotlin.data.network.Path.POST_REGISTER
import retrofit2.http.*
import retrofit2.http.Path

interface ApiService {
    @POST(POST_LOGIN)
    suspend fun postLogin(@Body loginRequest: LoginRequest): LoginResponse

    @POST(POST_REGISTER)
    suspend fun postRegister(@Body registerRequest: RegisterRequest): LoginResponse

    @GET(GET_INFO)
    suspend fun getInfo(@Path("user") user: String): InfoResponse

    @GET(GET_POKEMON)
    suspend fun getPokemon(@Query("limit") limit: Int): ResponsePokemon
}
