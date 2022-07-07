package com.voltaire.meuflix.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id : Long,
    val genre: String,
    val name: String,
    val urlImage: String,
    val cast: String,
    val description : String
) : Parcelable
