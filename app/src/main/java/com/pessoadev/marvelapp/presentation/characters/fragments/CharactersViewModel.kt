package com.pessoadev.marvelapp.presentation.characters.fragments

import androidx.lifecycle.MutableLiveData
import com.pessoadev.marvelapp.data.model.Character
import com.pessoadev.marvelapp.domain.repository.MarvelRepository
import com.pessoadev.marvelapp.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

class CharactersViewModel(private val marvelRepository: MarvelRepository) : BaseViewModel() {

    private var limit: Int = 20
    private var offset: Int = 1
    private var listCharacterHelper: MutableList<Character> = mutableListOf()

    val listCharacters: MutableLiveData<MutableList<Character>> = MutableLiveData()
    val listCharactersFavorites: MutableLiveData<MutableList<Character>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val errorConnection: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getCharacters() {
        isLoading.value = true
        scope.launch {
            try {
                val response = marvelRepository.getCharacters(limit, offset)

                //check if the new data from server is into local database and mark as favorite if it is.
                marvelRepository.getCharactersLocal().take(1).collect { localCharacters ->
                    response.data.results.forEach { character ->
                        val a = localCharacters.find { it.id == character.id }
                        if (a != null) character.isFavorite = true
                    }
                }

                listCharacterHelper.addAll(response.data.results)
                listCharacters.value = listCharacterHelper
                offset += limit
                errorConnection.value = false
            } catch (e: Exception) {
                //we can catch any error here using getError
                errorMessage.value = getError(e).getErrorMessage()
                errorConnection.value = true
            }
            isLoading.value = false
        }

    }

    fun saveCharacter(character: Character) {
        scope.launch {
            try {
                marvelRepository.saveCharacterLocal(character)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getFavorites() {
        try {
            scope.launch {
                marvelRepository.getCharactersLocal().collect {
                    listCharactersFavorites.value = it
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteCharacter(character: Character) {
        try {
            scope.launch {
                marvelRepository.deleteCharacterLocal(character)
                listCharacters.value?.find { it.id == character.id }.apply {
                    this?.isFavorite = false
                }

                listCharacters.notifyObserver()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun verifyLocalFavorites() {
        scope.launch {
            marvelRepository.getCharactersLocal().take(1).collect { localCharacters ->
                listCharacters.value?.forEach { it.isFavorite = false }
                localCharacters.forEach { character ->
                    listCharacters.value?.find { it.id == character.id }.apply {
                        this?.isFavorite = true
                    }
                }
                listCharacters.notifyObserver()
            }
        }

    }


}
