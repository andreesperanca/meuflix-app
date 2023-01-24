package com.voltaire.meuflix.retrofit.webclient

import com.voltaire.meuflix.models.Genre
import com.voltaire.meuflix.retrofit.AppRetrofit
import com.voltaire.meuflix.retrofit.service.WebService
import com.voltaire.meuflix.utils.apiCall
import com.voltaire.meuflix.utils.generics.makeRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GenreWebClient(
    private val service: WebService = AppRetrofit().webService,
) {

    suspend fun fetchHighlightGenres(
        genreId: String,
        page: String
        ) = service.fetchHighlightsGenres(genreId = genreId)

}