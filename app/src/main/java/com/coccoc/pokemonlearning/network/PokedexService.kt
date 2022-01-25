package com.coccoc.pokemonlearning.network

import com.coccoc.pokemonlearning.model.PokemonResponse
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface PokedexService {
    @GET("pokemon")
    fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Single<PokemonResponse>
}