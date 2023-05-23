package com.pil.movieapp.domain.usecase

import com.pil.movieapp.domain.database.MovieDataBaseInterface
import com.pil.movieapp.domain.service.MovieServiceInterface
import com.pil.movieapp.domain.util.CoroutineResult
import com.pil.movieapp.domain.entity.Movie

interface MovieUseCaseInterface {
    suspend operator fun invoke(): CoroutineResult<List<Movie>>
}

class GetMoviesUseCase(
    private val service: MovieServiceInterface,
    private val database: MovieDataBaseInterface
): MovieUseCaseInterface {
    override suspend operator fun invoke(): CoroutineResult<List<Movie>> {
        return when (val movies = service.getMovies()) {
            is CoroutineResult.Success -> {
                database.insertMovies(movies.data)
                database.getAllMovies()
            }

            is CoroutineResult.Failure -> {
                database.getAllMovies()
            }
        }
    }
}