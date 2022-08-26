package com.voltaire.meuflix.retrofit.webclient

import com.voltaire.meuflix.models.Movie
import com.voltaire.meuflix.retrofit.AppRetrofit
import com.voltaire.meuflix.retrofit.service.WebService
import com.voltaire.meuflix.utils.generics.makeRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesWebClient(
    private val service: WebService = AppRetrofit().webService,
) {

    fun getMoviesByGenre(
        genreName : String,
        onSuccess: (listMovies: List<Movie>?) -> Unit,
        onFailure: (error: String?) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            makeRequest<List<Movie>>(
                call = service.getAllMoviesByGenre(genreName),
                onFailure = onFailure,
                onSuccess = onSuccess
            )
        }
    }
}