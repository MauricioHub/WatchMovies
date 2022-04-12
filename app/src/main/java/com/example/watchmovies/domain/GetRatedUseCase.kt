package com.example.watchmovies.domain

import com.example.watchmovies.data.MovieRepository
import com.example.watchmovies.data.model.MovieModel

class GetRatedUseCase {

    private val repository = MovieRepository()

    suspend operator fun invoke(): List<MovieModel>? = repository.getAllRatedMovies()
}