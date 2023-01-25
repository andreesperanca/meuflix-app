package com.voltaire.meuflix.retrofit.service

import com.voltaire.meuflix.models.*
import org.intellij.lang.annotations.Language
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    @GET("discover/movie")
    suspend fun fetchHighlightsGenres(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("with_genres") genreId: String,
        @Query("page") page: String = "1"
    ): CategoryRequest

    @GET("movie/now_playing")
    suspend fun fetchHighlightsMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: String = "1"
    ) : CategoryRequest

    @GET("genre/movie/list")
    suspend fun fetchGenres(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE
    ) : GenreRequest


    companion object {
        const val API_KEY = "6fa456f78201a71982684b3d03f472b5"
        const val LANGUAGE = "pt-BR"
    }
}