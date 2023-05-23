package com.pil.movieapp.data.service.mapper


import com.pil.movieapp.data.entity.MovieEntity
import com.pil.movieapp.data.service.response.MovieList
import com.pil.movieapp.domain.entity.Movie

fun MovieList.mapToList(): List<Movie> {
    return results.map { movie ->
        Movie(
            title = movie.title,
            overview = movie.overview,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            originalLanguage = movie.originalLanguage,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount
        )
    }
}

fun MovieEntity.toMovie(): Movie {
    return Movie(
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        originalLanguage = this.originalLanguage,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        originalLanguage = this.originalLanguage,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun List<MovieEntity>.toMovieList(): List<Movie> {
    return this.map { it.toMovie() }
}