package com.example.watchmovies.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchmovies.data.model.MovieModel
import com.example.watchmovies.domain.GetMoviesUseCase
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {

    val favoriteMovies = MutableLiveData<List<MovieModel>>()
    val loading = MutableLiveData<Boolean>()

    val getMoviesUseCase = GetMoviesUseCase()

    fun onCreate(){
        viewModelScope.launch {
            loading.postValue(true)
            var result = getMoviesUseCase()

            if (!result.isNullOrEmpty()){
                favoriteMovies.postValue(result!!)
                loading.postValue(false)
            }
        }
    }
}