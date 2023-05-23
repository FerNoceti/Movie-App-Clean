package com.pil.movieapp.di

import com.pil.movieapp.data.service.MovieService
import com.pil.movieapp.domain.service.MovieServiceInterface
import org.koin.dsl.module

object ServiceModule {
    val movieServiceModule = module {
        factory<MovieServiceInterface> { MovieService(get()) }
    }
}