package com.pa.pakotlin.data.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/v2/pokemon/charizard?")
    fun getPokemon(@Query("limit") limit: Int): Response<ResponseBody>
}
