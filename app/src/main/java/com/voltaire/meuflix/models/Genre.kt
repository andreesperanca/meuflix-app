package com.voltaire.meuflix.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val id : Long,
    var name : String,
    var listMovies : List<Movie>
) : Parcelable
