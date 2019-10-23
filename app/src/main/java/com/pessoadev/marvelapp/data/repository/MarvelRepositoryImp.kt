package com.pessoadev.marvelapp.data.repository

import com.pessoadev.marvelapp.data.model.Character
import com.pessoadev.marvelapp.data.model.CharactersResponse
import com.pessoadev.marvelapp.data.model.ComicsResponse
import com.pessoadev.marvelapp.data.model.SerieResponse
import com.pessoadev.marvelapp.data.source.local.CharacterDao
import com.pessoadev.marvelapp.data.source.remote.ApiService
import com.pessoadev.marvelapp.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow

class MarvelRepositoryImp(
    private val apiService: ApiService,
    private val characterDao: CharacterDao
) : MarvelRepository {

    override suspend fun getCharacters(limit: Int, offset: Int): CharactersResponse {
        return apiService.getCharacters(limit, offset)
    }

    override suspend fun getComicsByCharacterId(id: String): ComicsResponse {
        return apiService.getComicsByCharacterId(id)
    }

    override suspend fun getSeriesByCharacterId(id: String): SerieResponse {
        return apiService.getSeriesByCharacterId(id)
    }

    override suspend fun saveCharacterLocal(character: Character): Long {
        return characterDao.insert(character)
    }



    override suspend fun getCharactersLocal(): Flow<MutableList<Character>> {
        return characterDao.getAll()
    }

    override suspend fun deleteCharacterLocal(character: Character) {
        return characterDao.delete(character)
    }
}