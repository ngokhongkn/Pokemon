package com.coccoc.pokemonlearning.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coccoc.pokemonlearning.R
import com.coccoc.pokemonlearning.databinding.ActivityMainBinding
import com.coccoc.pokemonlearning.ui.adapter.PokemonAdapter
import com.coccoc.pokemonlearning.utils.Status
import com.coccoc.pokemonlearning.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.recyclerView.setHasFixedSize(true)

        mainViewModel.fetchPokemonList()

        mainViewModel.getPokemonList().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(this, "success = " + it.data?.results?.size, Toast.LENGTH_LONG).show()
                    mAdapter = PokemonAdapter(it.data?.results!!)
                    binding.recyclerView.adapter = mAdapter
                }
                Status.ERROR -> {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(this, "error = " + it.message, Toast.LENGTH_LONG).show()
                }

                Status.LOADING -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
            }
        })
    }
}