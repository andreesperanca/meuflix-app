package com.voltaire.meuflix.viewmodel

import androidx.lifecycle.ViewModel
import com.voltaire.meuflix.models.Genre
import com.voltaire.meuflix.repositories.HomeRepository
import com.voltaire.meuflix.repositories.Resource

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel()
{

    fun getAllGenres() = repository.getAllGenres()
}