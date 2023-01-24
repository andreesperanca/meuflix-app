package com.voltaire.meuflix.retrofit.service

import com.voltaire.meuflix.models.Genre
import com.voltaire.meuflix.models.Movie
import com.voltaire.meuflix.models.Request
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
    ): Request


    companion object {
        const val API_KEY = "6fa456f78201a71982684b3d03f472b5"
    }
}