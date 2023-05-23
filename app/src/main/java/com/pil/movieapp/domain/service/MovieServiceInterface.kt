package com.pil.movieapp.domain.service


import com.pil.movieapp.domain.entity.Movie
import com.pil.movieapp.domain.util.CoroutineResult


interface MovieServiceInterface {
    suspend fun getMovies(): CoroutineResult<List<Movie>>
}
