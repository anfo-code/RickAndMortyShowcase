package com.example.rickyandmortyshowcase.database.local.domain

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.rickyandmortyshowcase.database.local.data.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    suspend fun upsertCharacter(favorite: Favorite)

    suspend fun  deleteCharacter(favorite: Favorite)

    fun getFavourites(): Flow<List<String>>
}