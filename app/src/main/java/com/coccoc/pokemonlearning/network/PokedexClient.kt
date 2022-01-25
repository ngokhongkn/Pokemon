package com.coccoc.pokemonlearning.network

import com.coccoc.pokemonlearning.model.PokemonResponse
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class PokedexClient @Inject constructor(
    private val pokedexService: PokedexService
) {
    fun fetchPokemonList(
        page: Int
    ): Single<PokemonResponse> =
        pokedexService.fetchPokemonList(
            limit = 20,
            offset = page * 20
        )
}