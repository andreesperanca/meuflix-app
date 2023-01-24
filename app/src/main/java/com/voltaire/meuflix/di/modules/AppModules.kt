package com.voltaire.meuflix.di.modules

import com.voltaire.meuflix.repositories.MoviesRepository
import com.voltaire.meuflix.repositories.MoviesRepositoryImpl
import com.voltaire.meuflix.retrofit.AppRetrofit
import com.voltaire.meuflix.retrofit.webclient.GenreWebClient
import com.voltaire.meuflix.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    single {
        AppRetrofit().webService
    }

    single <GenreWebClient> {
        GenreWebClient(service = get())
    }

    single <MoviesRepository> {
        MoviesRepositoryImpl(get ())
    }

    viewModel<MoviesViewModel> {
        MoviesViewModel(repository = get())
    }
}