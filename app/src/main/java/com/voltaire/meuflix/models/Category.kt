package com.voltaire.meuflix.models

data class Category(
    val name: String,
    val movieList: List<Movie>
)

data class CategoryRequest(
    val page: Int,
    val results: List<Movie>
)
