package com.pessoadev.marvelapp.data.source.remote

import com.pessoadev.marvelapp.data.model.CharactersResponse
import com.pessoadev.marvelapp.data.model.ComicsResponse
import com.pessoadev.marvelapp.data.model.SerieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): CharactersResponse

    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun getComicsByCharacterId(
        @Path("characterId") characterId: String
    ): ComicsResponse

    @GET("/v1/public/characters/{characterId}/series")
    suspend fun getSeriesByCharacterId(
        @Path("characterId") characterId: String
    ): SerieResponse

    @GET("/v1/public/characters/{characterId}/series")
    suspend fun getSeriesById(
        @Path("characterId") characterId: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): CharactersResponse
}