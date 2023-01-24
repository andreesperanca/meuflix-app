package com.voltaire.meuflix.models

data class Request(
    val page: Int,
    val results: List<Movie>
)
