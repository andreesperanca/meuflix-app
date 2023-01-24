package com.voltaire.meuflix.repositories

import com.voltaire.meuflix.models.Genre
import com.voltaire.meuflix.models.Request
import com.voltaire.meuflix.utils.Resource

interface MoviesRepository {

    suspend fun fetchHighlightsMovies() : Resource<Request>

    fun fetchHighlightsGenre() : List<Genre>

}