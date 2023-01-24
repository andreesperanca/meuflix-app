package com.voltaire.meuflix.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voltaire.meuflix.models.Request
import com.voltaire.meuflix.repositories.MoviesRepository
import com.voltaire.meuflix.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val repository: MoviesRepository
) : ViewModel() {
    private var _moviesResource = MutableLiveData<Resource<Request>>()
    val moviesResource: LiveData<Resource<Request>> = _moviesResource
    fun fetchMoviesWithGenre() {
        _moviesResource.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            val result = repository.fetchHighlightsMovies()
            _moviesResource.postValue(result)
        }
    }
}