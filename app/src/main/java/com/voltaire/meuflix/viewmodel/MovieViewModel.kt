package com.voltaire.meuflix.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.voltaire.meuflix.models.Genre
import com.voltaire.meuflix.models.Movie
import com.voltaire.meuflix.repositories.HomeRepository
import com.voltaire.meuflix.repositories.MovieRepository
import com.voltaire.meuflix.repositories.Resource

class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel()
{

    fun getSimilarMovies(genreName : String, onComplete: (Resource<List<Movie>>) -> Unit = {}) {
        return repository.getAllMoviesByGenre(genreName = genreName, onComplete = onComplete)
    }
}