package com.example.watchmovies.domain

import com.example.watchmovies.data.MovieRepository
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(name: String) =
        repository.getMoviesByNameFromDatabase(name)
}