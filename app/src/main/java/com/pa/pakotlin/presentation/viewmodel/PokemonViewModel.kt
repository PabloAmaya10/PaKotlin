package com.pa.pakotlin.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pa.pakotlin.domain.usecases.GetPokemonApiUseCase
import com.pa.pakotlin.presentation.model.PokemonModel
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    private val getPokemonApiUseCase = GetPokemonApiUseCase()

    private val _pokemon = MutableLiveData<PokemonModel>()

    val pokemon: LiveData<PokemonModel> get() = _pokemon

    fun getPokemonData() {
        viewModelScope.launch {
            val response = getPokemonApiUseCase(1000)
            _pokemon.postValue(response)
        }
    }
}
