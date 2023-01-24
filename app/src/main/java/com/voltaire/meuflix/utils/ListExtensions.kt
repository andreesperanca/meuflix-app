package com.voltaire.meuflix.utils

fun <G> List<G>.sortGenres() : List<G> {
    if (this.size <= 9) return this
    val range: IntRange = 0 until this.size
    val returnList: MutableList<G> = emptyList<G>() as MutableList<G>

    for (i in 0..8) {
        val randomIndex = range.random()
        val randomItem = this[randomIndex]
        returnList.add(randomItem)
    }
    return returnList
}