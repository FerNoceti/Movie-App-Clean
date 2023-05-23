package com.pil.movieapp.di

import com.pil.movieapp.data.service.MovieRequestGenerator
import org.koin.dsl.module

object RequestModule {
    val requestModule = module {
        factory { MovieRequestGenerator }
    }
}