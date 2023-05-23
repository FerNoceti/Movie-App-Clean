package com.pil.movieapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pil.movieapp.domain.entity.Movie
import com.pil.movieapp.domain.usecase.GetMoviesUseCase
import com.pil.movieapp.domain.util.CoroutineResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class MovieViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel(), KoinComponent {

    private val mutableLiveData: MutableLiveData<MovieData> = MutableLiveData()
    fun getValue(): LiveData<MovieData> = mutableLiveData

    fun callService() = viewModelScope.launch {
        withContext(Dispatchers.IO) { getMoviesUseCase() }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    mutableLiveData.value = MovieData(MovieStatus.SHOW_INFO, result.data)
                }
                is CoroutineResult.Failure -> {
                    mutableLiveData.value = MovieData(MovieStatus.EMPTY, emptyList())
                }
            }
        }
    }

    fun goBack() {
        mutableLiveData.value = MovieData(MovieStatus.GO_BACK, emptyList())
    }

    data class MovieData(
        val status: MovieStatus,
        val movies: List<Movie>,
    )

    enum class MovieStatus {
        SHOW_INFO,
        EMPTY,
        GO_BACK,
    }
}
