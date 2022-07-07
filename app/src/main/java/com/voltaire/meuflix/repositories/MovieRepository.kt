package com.voltaire.meuflix.repositories

import com.voltaire.meuflix.models.Movie
import com.voltaire.meuflix.retrofit.webclient.MoviesWebClient

class MovieRepository (private val webClient: MoviesWebClient) {

    private val resource = Resource<List<Movie>>()

    fun getAllMoviesByGenre(
        genreName : String,
        onComplete: (Resource<List<Movie>>) -> Unit = {}
    ) {
        webClient.getMoviesByGenre(
            genreName = genreName,
            onSuccess = { listMovies ->
                this.resource.data = listMovies
                this.resource.error = null
                onComplete(resource)
            },
            onFailure = { message ->
                this.resource.error = message
                this.resource.data = null
                onComplete(resource)
            })
    }
}