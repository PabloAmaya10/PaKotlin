package com.pa.pakotlin.domain.repository

import com.pa.pakotlin.data.network.Path.BASE_URL_POKEMON
import com.pa.pakotlin.data.network.RetrofitClient
import com.pa.pakotlin.presentation.model.PokemonModel
import com.pa.pakotlin.presentation.model.toModel

class PokemonRepository {
    suspend fun getPokemonData(limit: Int): PokemonModel {
        return RetrofitClient.api(BASE_URL_POKEMON).getPokemon(limit).toModel()
    }
}
