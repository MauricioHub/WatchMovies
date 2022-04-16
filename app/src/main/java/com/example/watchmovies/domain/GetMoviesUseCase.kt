package com.example.watchmovies.domain

import com.example.watchmovies.data.MovieRepository
import com.example.watchmovies.data.database.entities.toDatabase
import com.example.watchmovies.domain.model.MovieItem
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(category: String): List<MovieItem>{
        val movies = repository.getAllMoviesFromApi(category)

        return if (movies.isNotEmpty()){
            repository.clearSelectedMovies(category)
            repository.insertMovies(movies.map { it.toDatabase() })
            movies
        } else{
            repository.getAllMoviesFromDatabase(category)
        }
    }
}