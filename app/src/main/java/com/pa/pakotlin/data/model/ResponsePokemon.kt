/*
 * ResponsePokemon.kt
 * Android Kotlin App"
 * Created by Pablo Amaya on 05/09/2022, 13:03:18
 * Copyright (c) 2022 Develop-MX. All rights reserved
 */

package com.pa.pakotlin.data.model

import com.google.gson.annotations.SerializedName

data class PokemonName(@SerializedName("name") var name: String)

data class ResponsePokemon(@SerializedName("results") var listPokemon: List<PokemonName>)
