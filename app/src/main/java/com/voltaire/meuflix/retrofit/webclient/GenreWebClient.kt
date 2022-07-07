package com.voltaire.meuflix.retrofit.webclient

import com.voltaire.meuflix.models.Genre
import com.voltaire.meuflix.retrofit.AppRetrofit
import com.voltaire.meuflix.retrofit.service.WebService
import makeRequest

class GenreWebClient(
    private val service: WebService = AppRetrofit().webService,
) {

    fun getAllGenre(
        onSuccess: (listGenres: List<Genre>?) -> Unit,
        onFailure: (error: String?) -> Unit
    ) {
        makeRequest<List<Genre>>(
            call = service.getAllGenre(),
            onFailure = onFailure,
            onSuccess = onSuccess
        )
    }

}