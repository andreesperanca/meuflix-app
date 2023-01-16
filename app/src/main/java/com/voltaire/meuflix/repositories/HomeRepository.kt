package com.voltaire.meuflix.repositories

import com.voltaire.meuflix.SimpleCategory
import com.voltaire.meuflix.categories
import com.voltaire.meuflix.models.Genre
import com.voltaire.meuflix.retrofit.webclient.GenreWebClient

class HomeRepository(private val webClient: GenreWebClient) {

    private val resource = Resource<List<SimpleCategory>>()

    fun getAllGenres(
        onComplete: (Resource<List<Genre>>) -> Unit = {}
    ) = categories

}