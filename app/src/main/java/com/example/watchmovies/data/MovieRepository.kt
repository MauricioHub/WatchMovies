package com.example.watchmovies.data

import com.example.watchmovies.data.model.MovieModel
import com.example.watchmovies.data.network.MovieService

class MovieRepository {
    private val api = MovieService()

    suspend fun getAllFavoriteMovies(): List<MovieModel> {
        return api.getFavoriteMovies()
    }
}