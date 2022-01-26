package com.coccoc.pokemonlearning.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coccoc.pokemonlearning.databinding.ItemPokemonBinding
import com.coccoc.pokemonlearning.model.Pokemon

class PokemonAdapter constructor(
    private val mPokemonList: List<Pokemon>
) : RecyclerView.Adapter<PokemonAdapter.PokemonAdapterViewHolder>() {


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
        holder.bind(mPokemonList[position])
    }

    override fun getItemCount(): Int {
        return mPokemonList.size
    }
}