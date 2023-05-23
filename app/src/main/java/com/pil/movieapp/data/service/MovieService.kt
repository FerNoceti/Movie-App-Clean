package com.pil.movieapp.data.service

import com.pil.movieapp.data.service.api.MovieClient
import com.pil.movieapp.data.service.mapper.mapToList
import com.pil.movieapp.domain.entity.Movie
import com.pil.movieapp.domain.service.MovieServiceInterface
import com.pil.movieapp.domain.util.CoroutineResult


class MovieService(private val api: MovieRequestGenerator) : MovieServiceInterface {

    override suspend fun getMovies(): CoroutineResult<List<Movie>> {
        return try {
            val callResponse = api.createService(MovieClient::class.java).getData()
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    CoroutineResult.Success(it.mapToList())
                } ?: CoroutineResult.Failure(Exception("Response body is null"))
            } else {
                CoroutineResult.Failure(Exception("Response unsuccessful"))
            }
        } catch (e: Exception) {
            CoroutineResult.Failure(e)
        }
    }

}
