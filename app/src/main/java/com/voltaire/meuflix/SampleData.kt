package com.voltaire.meuflix

import androidx.annotation.DrawableRes
import com.voltaire.meuflix.models.Movie

data class SimpleMovie (
    @DrawableRes val  cover: Int
        )

data class SimpleCategory (
    val name: String,
    val movieList: List<SimpleMovie>
)

val movies = listOf<SimpleMovie>(
    SimpleMovie(R.drawable.movie_4),
    SimpleMovie(R.drawable.teste3),
    SimpleMovie(R.drawable.tes1),
    SimpleMovie(R.drawable.test5),
    SimpleMovie(R.drawable.teste2),
    SimpleMovie(R.drawable.teset35),
)


val categories = listOf<SimpleCategory>(
    SimpleCategory("Animações", movies.reversed()),
    SimpleCategory("Romances", movies),
    SimpleCategory("Comédia", movies),
    SimpleCategory("Comédia", movies),
    SimpleCategory("Comédia", movies),
    SimpleCategory("Comédia", movies),
    SimpleCategory("Comédia", movies),
    SimpleCategory("Comédia", movies),
    SimpleCategory("Comédia", movies)
)