package com.voltaire.meuflix.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: Long,
    @SerializedName("adult")
    val adult : Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genreIds")
    val genreIds: List<Int>,
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val posterPath : String,
    @SerializedName("overview")
    val overview : String,
    @SerializedName("vote_average")
    val vote_average : Float
) : Parcelable
