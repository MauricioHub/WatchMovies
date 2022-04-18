package com.example.watchmovies.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class SplashViewModel : ViewModel(){

    @ExperimentalCoroutinesApi
    val loading = MutableLiveData<Boolean>()
    init {
        viewModelScope.launch {
            delay(3000)
            loading.postValue(false)
        }
    }
}