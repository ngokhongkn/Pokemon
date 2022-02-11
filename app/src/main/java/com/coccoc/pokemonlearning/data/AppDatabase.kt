package com.coccoc.pokemonlearning.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.coccoc.pokemonlearning.data.dao.PokemonDao
import com.coccoc.pokemonlearning.model.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}