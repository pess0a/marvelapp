package com.pessoadev.marvelapp

import com.pessoadev.marvelapp.data.model.Character
import com.pessoadev.marvelapp.data.model.Data
import com.pessoadev.marvelapp.data.repository.MarvelRepositoryImp
import com.pessoadev.marvelapp.domain.repository.MarvelRepository
import org.mockito.Mockito

open class BaseUnitTest {

    var repoMock: MarvelRepository = Mockito.mock(MarvelRepositoryImp::class.java)

    fun createCharacter(): Data {
        val character1 = Character(id = "1", name = "Hulk", isFavorite = true)
        val character2 = Character(id = "2", name = "IronMan", isFavorite = false)
        val listCharacter = arrayListOf(character1, character2)
        return Data(listCharacter)
    }
}