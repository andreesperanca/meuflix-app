package com.voltaire.meuflix.repositories

import com.voltaire.meuflix.models.Genre
import com.voltaire.meuflix.retrofit.webclient.GenreWebClient

class HomeRepository(private val webClient: GenreWebClient) {

    private val resource = Resource<List<Genre>>()

    fun getAllGenres(
        onComplete: (Resource<List<Genre>>) -> Unit = {}
    ) {
        webClient.getAllGenre(
            onSuccess = { listGenre ->
                this.resource.data = listGenre
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