package com.voltaire.meuflix.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre (
    val id: Int,
    val name: String
) : Parcelable

data class GenreRequest (
   val genres: List<Genre>
)