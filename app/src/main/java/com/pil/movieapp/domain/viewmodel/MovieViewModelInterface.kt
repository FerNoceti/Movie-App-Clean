package com.pil.movieapp.domain.viewmodel

import androidx.lifecycle.LiveData
import com.pil.movieapp.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.Job

interface MovieViewModelInterface {
    fun getValue(): LiveData<MovieViewModel.MovieData>
    fun callService() : Job
    fun goBack()
}