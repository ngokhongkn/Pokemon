package com.coccoc.pokemonlearning.ui.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import io.reactivex.android.schedulers.AndroidSchedulers

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: PokemonAdapter

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.recyclerView.setHasFixedSize(true)

        mAdapter = PokemonAdapter()
        binding.recyclerView.adapter = mAdapter
        binding.progressbar.visibility = View.VISIBLE
        mainViewModel.pokemonList.observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    list ->
                    binding.progressbar.visibility = View.GONE
                    mAdapter.submitList(list)
                    binding.recyclerView.adapter = mAdapter
                },
                {
                    e ->
                    binding.progressbar.visibility = View.GONE
                    Log.d("DungTest", "onCreate error: ${e.localizedMessage}")
                }
            )
    }
}