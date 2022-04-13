package com.example.watchmovies.domain

import com.example.watchmovies.data.MovieRepository
import com.example.watchmovies.data.model.MovieModel
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(): List<MovieModel>? = repository.getAllFavoriteMovies()
}