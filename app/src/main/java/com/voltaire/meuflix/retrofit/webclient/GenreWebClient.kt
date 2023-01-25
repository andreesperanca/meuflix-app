package com.voltaire.meuflix.retrofit.webclient

import com.voltaire.meuflix.retrofit.AppRetrofit
import com.voltaire.meuflix.retrofit.service.WebService

class GenreWebClient(
    private val service: WebService = AppRetrofit().webService,
) {
    suspend fun fetchHighlightGenres(genreId: String) = service.fetchHighlightsGenres(genreId = genreId)
    suspend fun fetchHighLightMovies() = service.fetchHighlightsMovies()
    suspend fun fetchGenres() = service.fetchGenres()

}