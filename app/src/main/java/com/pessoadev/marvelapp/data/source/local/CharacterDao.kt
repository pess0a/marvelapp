package com.pessoadev.marvelapp.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.pessoadev.marvelapp.data.model.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters_table")
    fun getAll(): Flow<MutableList<Character>>

    @Query("SELECT * FROM characters_table WHERE id=:id ")
    suspend fun getCharacterById(id: String): Character

    @Insert
    suspend fun insert(character: Character): Long

    @Delete
    suspend fun delete(character: Character)
}