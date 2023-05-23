package com.pil.movieapp.domain.database


import com.pil.movieapp.domain.entity.Movie
import com.pil.movieapp.domain.util.CoroutineResult


interface MovieDataBaseInterface {
    suspend fun insertMovies(moviesList: List<Movie>)
    suspend fun getAllMovies(): CoroutineResult<List<Movie>>
}
