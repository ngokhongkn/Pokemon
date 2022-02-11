package com.coccoc.pokemonlearning.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.coccoc.pokemonlearning.model.Pokemon
import com.coccoc.pokemonlearning.model.PokemonResponse
import com.coccoc.pokemonlearning.repository.MainRepository
import com.coccoc.pokemonlearning.ui.paging.PokemonDataSourceFactory
import com.coccoc.pokemonlearning.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokemonDataSourceFactory: PokemonDataSourceFactory
): ViewModel() {
//    private val pokemonList = MutableLiveData<Resource<PokemonResponse>>()
    val pokemonList: Observable<PagedList<Pokemon>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setPrefetchDistance(10)
            .setEnablePlaceholders(false)
            .build()

        pokemonList = RxPagedListBuilder(pokemonDataSourceFactory, config)
            .setFetchScheduler(Schedulers.io())
            .buildObservable()
            .cache()
    }

//    fun fetchPokemonList() {
//
//        val config = PagedList.Config.Builder()
//            .setPageSize(20)
//            .setPrefetchDistance(10)
//            .setEnablePlaceholders(false)
//            .build()
////        pokemonList.postValue(Resource.loading(null))
//        compositeDisposable.add(
//            mainRepository.fetchPokemonList(0)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ data ->
//                    pokemonList.postValue(Resource.success(data))
//                }, { throwable ->
//                    pokemonList.postValue(Resource.error(throwable.localizedMessage, null))
//
//                })
//        )
//
//
//
//    }
//
//    fun getPokemonList(): LiveData<Resource<PokemonResponse>> {
//        return pokemonList
//    }
}