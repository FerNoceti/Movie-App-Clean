package com.pil.movieapp.di

import com.pil.movieapp.domain.usecase.GetMoviesUseCase
import org.koin.dsl.module

object UseCaseModule {
    val useCaseModule = module {
        factory { GetMoviesUseCase(get(), get()) }
    }
}