package com.voltaire.meuflix.repositories

import com.voltaire.meuflix.models.*
import com.voltaire.meuflix.utils.Resource

interface MoviesRepository {

    suspend fun fetchHighlightsCategories() : Resource<List<Category>>
    suspend fun fetchHighLightsMovies() : Resource<List<Movie>>
    suspend fun fetchGenres()

}