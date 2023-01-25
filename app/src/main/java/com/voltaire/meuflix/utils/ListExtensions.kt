package com.voltaire.meuflix.utils

fun <G> List<G>.sortGenres(): List<G> {
    if (this.size <= 9) return this
    val range: IntRange = 0 until this.size
    val gList = mutableListOf<G>()
    gList.apply {
        while (this.size < 8) {
            val randomIndex = range.random()
            if (!this.contains(this@sortGenres[randomIndex])) {
                this.add(this@sortGenres[randomIndex])
            }
        }
    }
    return gList
}