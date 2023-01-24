package com.voltaire.meuflix.repositories

import com.voltaire.meuflix.categories
import com.voltaire.meuflix.models.Genre
import com.voltaire.meuflix.models.Movie
import com.voltaire.meuflix.models.Request
import com.voltaire.meuflix.retrofit.webclient.GenreWebClient
import com.voltaire.meuflix.utils.Resource
import com.voltaire.meuflix.utils.Resource.*
import com.voltaire.meuflix.utils.apiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(
    private val webClient: GenreWebClient
) : MoviesRepository {

    override suspend fun fetchHighlightsMovies(): Resource<Request> {
        return withContext(Dispatchers.IO) {
            apiCall {
                val fetchResult = webClient.fetchHighlightGenres(genreId = "16", page = "1")
                Success(fetchResult)
            }
        }
    }

    override fun fetchHighlightsGenre(): List<Genre> {
        TODO("Not yet implemented")
    }
}