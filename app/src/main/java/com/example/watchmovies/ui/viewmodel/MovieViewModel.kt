package com.example.watchmovies.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchmovies.domain.GetMoviesUseCase
import com.example.watchmovies.domain.GetTrailersUseCase
import com.example.watchmovies.domain.SearchMoviesUseCase
import com.example.watchmovies.domain.model.MovieItem
import com.example.watchmovies.domain.model.TrailerItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
                                private val getMoviesUseCase: GetMoviesUseCase,
                                private val getTrailersUseCase: GetTrailersUseCase,
                                private val searchMoviesUseCase: SearchMoviesUseCase): ViewModel() {

    val allMoviesLst = MutableLiveData<List<MovieItem>>()
    val allTrailersLst = MutableLiveData<List<TrailerItem>>()
    val foundMoviesLst = MutableLiveData<List<MovieItem>>()
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

    fun fetchAllTrailers(codeMovie: String, apiKey: String){
        viewModelScope.launch {
            val result = getTrailersUseCase(codeMovie, apiKey)

            if (!result.isNullOrEmpty()){
                allTrailersLst.postValue(result)
            }
        }
    }

    fun fetchMoviesByName(name: String){
        viewModelScope.launch {
            val result = searchMoviesUseCase(name)

            if (!result.isNullOrEmpty()){
                foundMoviesLst.postValue(result)
            }
        }
    }
}