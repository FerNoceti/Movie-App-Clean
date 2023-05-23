package com.pil.movieapp.data.service.api

import com.pil.movieapp.data.service.response.MovieList
import retrofit2.Call
import retrofit2.http.GET

interface MovieClient {
    @GET("/3/movie/popular")
    fun getData(): Call<MovieList>
}
