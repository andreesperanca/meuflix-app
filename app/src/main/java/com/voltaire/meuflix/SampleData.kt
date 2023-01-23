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
    SimpleMovie(R.drawable.movie11),
    SimpleMovie(R.drawable.movie2),
    SimpleMovie(R.drawable.movie3),
    SimpleMovie(R.drawable.movie4),
    SimpleMovie(R.drawable.movie5),
    SimpleMovie(R.drawable.movie6),
    SimpleMovie(R.drawable.movie7),
    SimpleMovie(R.drawable.movie8),
    SimpleMovie(R.drawable.movie10)
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