package com.pil.movieapp.data.database


import com.pil.movieapp.domain.database.MovieDataBaseInterface
import com.pil.movieapp.data.service.mapper.toMovieEntity
import com.pil.movieapp.data.service.mapper.toMovieList
import com.pil.movieapp.domain.entity.Movie
import com.pil.movieapp.domain.util.CoroutineResult
import java.lang.Exception

class MovieDataBase(private val movieDao: MovieDao) : MovieDataBaseInterface {

    override suspend fun insertMovies(moviesList: List<Movie>) {
        moviesList.forEach {
            movieDao.insertMovie(it.toMovieEntity())
        }
    }

    override suspend fun getAllMovies(): CoroutineResult<List<Movie>> =
        movieDao.getDBMovies().let {
            if (it.isNotEmpty()) {
                CoroutineResult.Success(it.toMovieList())
            } else {
                CoroutineResult.Failure(Exception())
            }
        }


}