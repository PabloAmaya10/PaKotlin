package com.pa.pakotlin.domain.usecases

import com.pa.pakotlin.domain.repository.PokemonRepository

class GetPokemonApiUseCase {
    private val pokemonRepository = PokemonRepository()
    suspend operator fun invoke(limit: Int) = pokemonRepository.getPokemonData(limit)
}
