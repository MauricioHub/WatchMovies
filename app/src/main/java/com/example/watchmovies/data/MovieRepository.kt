package com.example.watchmovies.data

import com.example.watchmovies.data.model.MovieModel
import com.example.watchmovies.data.network.MovieService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: MovieService){

    suspend fun getAllFavoriteMovies(): List<MovieModel> {
        return api.getFavoriteMovies()
    }

    suspend fun getAllRatedMovies(): List<MovieModel> {
        return api.getRatedMovies()
    }
}