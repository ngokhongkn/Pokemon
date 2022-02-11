package com.coccoc.pokemonlearning.di

import android.app.Application
import androidx.room.Room
import com.coccoc.pokemonlearning.data.AppDatabase
import com.coccoc.pokemonlearning.data.dao.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application
    ): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "PokemonKotlin.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(
        appDatabase: AppDatabase
    ): PokemonDao {
        return appDatabase.pokemonDao()
    }
}