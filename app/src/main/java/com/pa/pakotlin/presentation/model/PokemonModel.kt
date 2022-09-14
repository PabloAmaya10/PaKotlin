package com.pa.pakotlin.presentation.model

import com.pa.pakotlin.data.model.ResponsePokemon

data class PokemonName(var name: String, var checkBox: Boolean = false)

data class PokemonModel(var listPokemon: List<PokemonName>)

fun ResponsePokemon.toModel(): PokemonModel =
    PokemonModel(listPokemon.map { PokemonName(it.name) })
