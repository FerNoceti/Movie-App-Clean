package com.pil.movieapp.presentation.di

import com.pil.movieapp.presentation.viewmodel.MenuViewModel
import com.pil.movieapp.presentation.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val movieViewModelModule = module {
        viewModel { MovieViewModel(get()) }
    }
    val menuViewModelModule = module {
        viewModel { MenuViewModel() }
    }
}