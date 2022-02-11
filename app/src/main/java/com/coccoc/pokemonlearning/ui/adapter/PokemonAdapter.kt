package com.coccoc.pokemonlearning.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coccoc.pokemonlearning.databinding.ItemPokemonBinding
import com.coccoc.pokemonlearning.model.Pokemon

class PokemonAdapter : PagedListAdapter<Pokemon, PokemonAdapter.PokemonAdapterViewHolder>(
    pokemonDiff) {


    class PokemonAdapterViewHolder(private var itemPokemonBinding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(itemPokemonBinding.root) {
        fun bind(pokemon: Pokemon) {
            itemPokemonBinding.pokemon = pokemon
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapterViewHolder {
        val itemPokemonBinding =
            ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonAdapterViewHolder(itemPokemonBinding)
    }

    override fun onBindViewHolder(holder: PokemonAdapterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        val pokemonDiff = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(old: Pokemon, new: Pokemon): Boolean {
                return old.name == new.name
            }

            override fun areContentsTheSame(old: Pokemon, new: Pokemon): Boolean {
                return old == new
            }
        }
    }
}