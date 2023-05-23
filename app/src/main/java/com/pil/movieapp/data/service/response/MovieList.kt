package com.pil.movieapp.data.service.response

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("results")
    var results: List<MovieResponse>,
)