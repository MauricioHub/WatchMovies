package com.example.watchmovies.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchmovies.data.model.MovieModel
import com.example.watchmovies.domain.GetMoviesUseCase
import com.example.watchmovies.domain.GetRatedUseCase
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {

    val allMoviesLst = MutableLiveData<List<MovieModel>>()
    val loading = MutableLiveData<Boolean>()

    val getMoviesUseCase = GetMoviesUseCase()
    val getRatedUseCase = GetRatedUseCase()

    fun fetchFavoriteMovies(){
        viewModelScope.launch {
            loading.postValue(true)
            var result = getMoviesUseCase()

            if (!result.isNullOrEmpty()){
                allMoviesLst.postValue(result!!)
                loading.postValue(false)
            }
        }
    }

    fun fetchRatedMovies(){
        viewModelScope.launch {
            loading.postValue(true)
           var result = getRatedUseCase()

            if (!result.isNullOrEmpty()){
                allMoviesLst.postValue(result!!)
                loading.postValue(false)
            }
        }
    }
}