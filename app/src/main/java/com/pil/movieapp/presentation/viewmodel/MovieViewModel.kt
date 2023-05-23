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

    private val mutableLiveData: MutableLiveData<MainData> = MutableLiveData()
    fun getValue(): LiveData<MainData> = mutableLiveData

    fun callService() = viewModelScope.launch {
        withContext(Dispatchers.IO) { getMoviesUseCase() }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    if (result.data.isEmpty()) {
                        mutableLiveData.value = MainData(MainStatus.EMPTY, emptyList())
                    } else {
                        mutableLiveData.value = MainData(MainStatus.SHOW_INFO, result.data)
                    }
                }

                is CoroutineResult.Failure -> {
                    mutableLiveData.value = MainData(MainStatus.EMPTY, emptyList())
                }
            }
        }
    }

    fun goBack() {
        mutableLiveData.value = MainData(MainStatus.GO_BACK, emptyList())
    }

    data class MainData(
        val status: MainStatus,
        val movies: List<Movie>,
    )

    enum class MainStatus {
        SHOW_INFO,
        EMPTY,
        GO_BACK,
    }
}
