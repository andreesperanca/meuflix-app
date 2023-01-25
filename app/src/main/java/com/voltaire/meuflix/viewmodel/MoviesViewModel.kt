package com.voltaire.meuflix.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voltaire.meuflix.models.Category
import com.voltaire.meuflix.models.Genre
import com.voltaire.meuflix.models.Movie
import com.voltaire.meuflix.repositories.MoviesRepository
import com.voltaire.meuflix.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _loadingUI = MutableLiveData<Boolean>()
    val loadingUI: LiveData<Boolean> = _loadingUI

    private var _moviesResource = MutableLiveData<Resource<List<Movie>>>()
    val moviesResource: LiveData<Resource<List<Movie>>> = _moviesResource

    private var _categoriesResource = MutableLiveData<Resource<List<Category>>>()
    val categoriesResource: LiveData<Resource<List<Category>>> = _categoriesResource
    fun fetchHighlightsMovies() {
        setLoadingUI(true)
        viewModelScope.launch(Dispatchers.Main) {
            val result = repository.fetchHighLightsMovies()
            _moviesResource.value = result
        }
    }
    fun fetchHighlightsCategories() {
        setLoadingUI(true)
        viewModelScope.launch(Dispatchers.Main) {
            val result = repository.fetchHighlightsCategories()
            _categoriesResource.value = result
        }
    }

    fun setLoadingUI(boolean: Boolean) {
        _loadingUI.value = boolean
    }

}