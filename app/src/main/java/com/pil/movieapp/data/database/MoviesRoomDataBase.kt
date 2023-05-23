package com.pil.movieapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pil.movieapp.data.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 1,
)
abstract class MoviesRoomDataBase : RoomDatabase() {
    abstract fun moviesDao(): MovieDao
}
