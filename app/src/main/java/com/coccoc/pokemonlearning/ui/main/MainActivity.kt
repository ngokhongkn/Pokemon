package com.coccoc.pokemonlearning.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coccoc.pokemonlearning.R
import com.coccoc.pokemonlearning.utils.Status
import com.coccoc.pokemonlearning.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.fetchPokemonList()

        mainViewModel.getPokemonList().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(this, "success = " + it.data?.results?.size, Toast.LENGTH_LONG).show()
                }
                Status.ERROR -> {
                    Toast.makeText(this, "error = " + it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}