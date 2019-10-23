package com.pessoadev.marvelapp

import com.pessoadev.marvelapp.data.model.CharactersResponse
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.`when`

class UnitTest : BaseUnitTest() {

    @Test
    fun testCharacterList() {
        runBlocking {
            val responseMock = CharactersResponse(createCharacter())
            `when`(repoMock.getCharacters(20, 1)).thenReturn(responseMock)
            assertEquals(
                responseMock.data.results.size,
                repoMock.getCharacters(20, 1).data.results.size
            )
        }
    }

    @Test
    fun testCharacterListNotNull() {
        runBlocking {
            val responseMock = CharactersResponse(createCharacter())
            `when`(repoMock.getCharacters(20, 1)).thenReturn(responseMock)
            responseMock.data.results.forEach {
                assertNotNull(it)
            }
        }
    }

    @Test
    fun testCharacterFavorite() {
        runBlocking {
            val responseMock = CharactersResponse(createCharacter())
            `when`(repoMock.getCharacters(20, 1)).thenReturn(responseMock)
            assertEquals(
                responseMock.data.results[0].isFavorite,
                repoMock.getCharacters(20, 1).data.results[0].isFavorite
            )
        }
    }
}
