package com.voltaire.meuflix.retrofit.service

import com.voltaire.meuflix.models.Genre
import com.voltaire.meuflix.models.Movie
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    @GET("genres")
    fun getAllGenre(): Call<List<Genre>>

    @GET("movies")
    fun getAllMoviesByGenre(@Query("genreName") name: String): Call<List<Movie>>

}