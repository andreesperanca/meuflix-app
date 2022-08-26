package com.voltaire.meuflix.di.modules

import com.voltaire.meuflix.repositories.HomeRepository
import com.voltaire.meuflix.repositories.MovieRepository
import com.voltaire.meuflix.retrofit.AppRetrofit
import com.voltaire.meuflix.retrofit.webclient.GenreWebClient
import com.voltaire.meuflix.retrofit.webclient.MoviesWebClient
import com.voltaire.meuflix.viewmodel.HomeViewModel
import com.voltaire.meuflix.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    single {
        AppRetrofit().webService
    }

    single <MoviesWebClient> {
        MoviesWebClient(service = get ())
    }

    single <GenreWebClient> {
        GenreWebClient(service = get())
    }

    single <MovieRepository> {
        MovieRepository(get())
    }

    single <HomeRepository> {
        HomeRepository(get ())
    }

    viewModel<HomeViewModel> {
        HomeViewModel(repository = get())
    }

    viewModel <MovieViewModel>{
        MovieViewModel(repository = get())
    }

}