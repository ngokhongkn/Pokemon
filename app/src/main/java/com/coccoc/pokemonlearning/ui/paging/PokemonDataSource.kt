package com.coccoc.pokemonlearning.ui.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.coccoc.pokemonlearning.data.dao.PokemonDao
import com.coccoc.pokemonlearning.model.Pokemon
import com.coccoc.pokemonlearning.repository.MainRepository
import io.reactivex.disposables.CompositeDisposable

class PokemonDataSource(
    private val mainRepository: MainRepository,
    private val compositeDisposable: CompositeDisposable,
    private val pokemonDao: PokemonDao
): PageKeyedDataSource<Int, Pokemon>() {
    override fun loadInitial(p0: LoadInitialParams<Int>, p1: LoadInitialCallback<Int, Pokemon>) {
        createObservable(0, 1, p1, null)
    }

    override fun loadBefore(p0: LoadParams<Int>, p1: LoadCallback<Int, Pokemon>) {
    }

    override fun loadAfter(p0: LoadParams<Int>, p1: LoadCallback<Int, Pokemon>) {
        val page = p0.key
        createObservable(page, page + 1, null, p1)
    }

    private fun createObservable(
        requestedPage: Int,
        adjacentPage: Int,
        initialCallback: LoadInitialCallback<Int, Pokemon>?,
        callback: LoadCallback<Int, Pokemon>?
    ) {
        var pokemonList = pokemonDao.getPokemonList(requestedPage)
        if (pokemonList.isEmpty()) {
            compositeDisposable.add(
                mainRepository.fetchPokemonList(requestedPage)
                    .subscribe(
                        { response ->
                            pokemonList = response.results
                            pokemonList.forEach {pokemon -> pokemon.page = requestedPage }
                            pokemonDao.insertPokemonList(pokemonList)
                            initialCallback?.onResult(pokemonDao.getPokemonList(requestedPage), null, adjacentPage)
                            callback?.onResult(pokemonDao.getPokemonList(requestedPage), adjacentPage)
                        },
                        { error ->
                            Log.e("DungTest", "error", error)
                        }
                    )
            )
        } else {
            initialCallback?.onResult(pokemonList, null, adjacentPage)
            callback?.onResult(pokemonList, adjacentPage)
        }

    }
}
