package com.pil.movieapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pil.movieapp.domain.viewmodel.MenuViewModelInterface

class MenuViewModel : ViewModel(), MenuViewModelInterface {

    private val mutableLiveData: MutableLiveData<MenuStates> = MutableLiveData()
    override fun getValue(): LiveData<MenuStates> {
        return mutableLiveData
    }

    override fun buttonPressed() {
        mutableLiveData.value = MenuStates.GO_TO_MOVIE_SCREEN
    }

    override fun buttonErrorPressed() {
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
