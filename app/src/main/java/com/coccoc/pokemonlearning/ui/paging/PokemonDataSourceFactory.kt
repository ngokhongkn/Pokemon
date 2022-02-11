package com.coccoc.pokemonlearning.ui.paging

import androidx.paging.DataSource
import com.coccoc.pokemonlearning.data.dao.PokemonDao
import com.coccoc.pokemonlearning.model.Pokemon
import com.coccoc.pokemonlearning.repository.MainRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PokemonDataSourceFactory @Inject constructor(
    private val mainRepository: MainRepository,
    private val pokemonDao: PokemonDao
): DataSource.Factory<Int, Pokemon>() {
    override fun create(): DataSource<Int, Pokemon> {
        val compositeDisposable = CompositeDisposable()
        return PokemonDataSource(mainRepository, compositeDisposable, pokemonDao)
    }
}