package com.pil.movieapp.di

import com.pil.movieapp.data.database.MovieDataBase
import com.pil.movieapp.domain.database.MovieDataBaseInterface
import org.koin.dsl.module

object DataBaseModule {

    val movieDataBaseModule = module {
        factory<MovieDataBaseInterface> { MovieDataBase(get()) }
    }
}