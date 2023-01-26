package com.voltaire.meuflix.repositories

import androidx.lifecycle.MutableLiveData
import com.voltaire.meuflix.models.Category
import com.voltaire.meuflix.models.Genre
import com.voltaire.meuflix.models.GenreRequest
import com.voltaire.meuflix.models.Movie
import com.voltaire.meuflix.retrofit.webclient.GenreWebClient
import com.voltaire.meuflix.utils.Resource
import com.voltaire.meuflix.utils.Resource.Success
import com.voltaire.meuflix.utils.apiCall
import com.voltaire.meuflix.utils.sortGenres
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(
    private val webClient: GenreWebClient
) : MoviesRepository {

    private val _genres = MutableLiveData<List<Genre>?>()
    private val genres: MutableLiveData<List<Genre>?> = _genres

    init {
        CoroutineScope(Dispatchers.IO).launch { fetchGenres() }
    }

    override suspend fun fetchHighlightsCategories(): Resource<List<Category>> {
        return withContext(Dispatchers.IO) {
            apiCall {
                val categories = mutableListOf<Category>()
                if (genres.value == null) { fetchGenres() }

                genres.value?.forEach { genre ->
                    val movieList = webClient.fetchHighlightGenres(genre.id.toString())
                    categories.add(Category(name = genre.name, movieList = movieList.results))
                }
                Success(categories)
            }
        }
    }
    override suspend fun fetchHighLightsMovies(): Resource<List<Movie>> {
        return withContext(Dispatchers.IO) {
            apiCall {
                val fetchResult = webClient.fetchHighLightMovies()
                Success(fetchResult.results)
            }
        }
    }
    override suspend fun fetchGenres() {
        return withContext(Dispatchers.Main) {
            apiCall {
                val genres: List<Genre> = webClient.fetchGenres().genres.sortGenres()
                _genres.value = genres
                Success(data = genres)
            }
        }
    }
}