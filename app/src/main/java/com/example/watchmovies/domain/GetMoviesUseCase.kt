package com.example.watchmovies.domain

import com.example.watchmovies.data.MovieRepository
import com.example.watchmovies.data.model.MovieModel

class GetMoviesUseCase {

    private val repository = MovieRepository()

    suspend operator fun invoke(): List<MovieModel>? = repository.getAllFavoriteMovies()
}