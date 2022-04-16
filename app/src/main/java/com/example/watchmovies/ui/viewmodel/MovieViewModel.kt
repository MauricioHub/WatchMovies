package com.example.watchmovies.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchmovies.domain.GetMoviesUseCase
import com.example.watchmovies.domain.GetRatedUseCase
import com.example.watchmovies.domain.model.MovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase,
                                         private val getRatedUseCase: GetRatedUseCase): ViewModel() {

    val allMoviesLst = MutableLiveData<List<MovieItem>>()
    val loading = MutableLiveData<Boolean>()

    fun fetchMoviesByCategory(category: String){
        viewModelScope.launch {
            loading.postValue(true)
            var result = getMoviesUseCase(category)

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