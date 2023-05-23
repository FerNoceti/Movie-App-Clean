package com.pil.movieapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
class MovieEntity(
    @PrimaryKey
    var id: Int,
    var title: String,
    var overview: String,
    var posterPath: String,
    var releaseDate: String,
    var originalLanguage: String,
    var voteAverage: Float,
    var voteCount: Int,
)
