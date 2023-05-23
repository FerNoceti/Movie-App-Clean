package com.pil.movieapp.presentation

import android.app.Application
import com.pil.movieapp.di.ServiceModule.movieServiceModule
import com.pil.movieapp.di.DataBaseModule.movieDataBaseModule
import com.pil.movieapp.di.RequestModule.requestModule
import com.pil.movieapp.di.RoomDBModule.roomDBModule
import com.pil.movieapp.di.UseCaseModule.useCaseModule
import com.pil.movieapp.presentation.di.ViewModelModule.menuViewModelModule
import com.pil.movieapp.presentation.di.ViewModelModule.movieViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieApp)
            modules(
                menuViewModelModule,
                movieViewModelModule,
                roomDBModule,
                movieServiceModule,
                requestModule,
                useCaseModule,
                movieDataBaseModule,
            )
        }
    }
}