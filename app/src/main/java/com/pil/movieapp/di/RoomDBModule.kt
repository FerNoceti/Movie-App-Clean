package com.pil.movieapp.di

import androidx.room.Room
import com.pil.movieapp.data.database.MoviesRoomDataBase
import org.koin.dsl.module

object RoomDBModule {

    val roomDBModule = module {
        single {
            Room.databaseBuilder(get(), MoviesRoomDataBase::class.java, "Movie-DataBase").build()
        }
        single { get<MoviesRoomDataBase>().moviesDao() }
    }
}