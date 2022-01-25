package com.coccoc.pokemonlearning.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coccoc.pokemonlearning.model.PokemonResponse
import com.coccoc.pokemonlearning.repository.MainRepository
import com.coccoc.pokemonlearning.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel(){
    private val pokemonList = MutableLiveData<Resource<PokemonResponse>>()
    private val compositeDisposable = CompositeDisposable()

    fun fetchPokemonList() {
        pokemonList.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.fetchPokemonList(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    pokemonList.postValue(Resource.success(data))
                }, { throwable ->
                    pokemonList.postValue(Resource.error(throwable.localizedMessage, null))

                })
        )

    }

    fun getPokemonList(): LiveData<Resource<PokemonResponse>> {
        return pokemonList
    }
}