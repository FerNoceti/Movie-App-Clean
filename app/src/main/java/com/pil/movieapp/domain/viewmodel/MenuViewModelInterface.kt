package com.pil.movieapp.domain.viewmodel

import androidx.lifecycle.LiveData
import com.pil.movieapp.presentation.viewmodel.MenuViewModel

interface MenuViewModelInterface {
    fun getValue(): LiveData<MenuViewModel.MenuStates>
    fun buttonPressed()
    fun buttonErrorPressed()
}