package com.coccoc.pokemonlearning.repository

import com.coccoc.pokemonlearning.model.PokemonResponse
import com.coccoc.pokemonlearning.network.PokedexClient
import io.reactivex.Single
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val  pokedexClient: PokedexClient
){
    fun fetchPokemonList(
        page: Int
    ): Single<PokemonResponse> {
        return pokedexClient.fetchPokemonList(page)
    }

}