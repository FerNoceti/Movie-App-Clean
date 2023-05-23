package com.pil.movieapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuViewModel : ViewModel() {

    private val mutableLiveData: MutableLiveData<MenuStates> = MutableLiveData()
    fun getValue(): LiveData<MenuStates> {
        return mutableLiveData
    }

    fun buttonPressed() {
        mutableLiveData.value = MenuStates.GO_TO_MOVIE_SCREEN
    }

    fun buttonErrorPressed() {
        mutableLiveData.value = MenuStates.ERROR
    }

    init {
        mutableLiveData.value = MenuStates.INIT
    }

    enum class MenuStates {
        INIT,
        GO_TO_MOVIE_SCREEN,
        ERROR,
    }
}
