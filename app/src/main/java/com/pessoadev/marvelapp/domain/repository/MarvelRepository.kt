package com.pessoadev.marvelapp.domain.repository

import com.pessoadev.marvelapp.data.model.Character
import com.pessoadev.marvelapp.data.model.CharactersResponse
import com.pessoadev.marvelapp.data.model.ComicsResponse
import com.pessoadev.marvelapp.data.model.SerieResponse
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {

    //remote
    suspend fun getCharacters(limit: Int, offset: Int): CharactersResponse
    suspend fun getComicsByCharacterId(id: String): ComicsResponse
    suspend fun getSeriesByCharacterId(id: String): SerieResponse

    //local
    suspend fun saveCharacterLocal(character: Character): Long
    suspend fun getCharactersLocal(): Flow<MutableList<Character>>
    suspend fun deleteCharacterLocal(character: Character)
}