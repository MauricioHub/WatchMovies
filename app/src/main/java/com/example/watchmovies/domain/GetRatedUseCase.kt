package com.example.watchmovies.domain

import com.example.watchmovies.data.MovieRepository
import com.example.watchmovies.data.database.entities.toDatabase
import com.example.watchmovies.domain.model.MovieItem
import javax.inject.Inject

class GetRatedUseCase @Inject constructor(private val repository: MovieRepository){

    suspend operator fun invoke(): List<MovieItem>{
        val movies = repository.getFavoriteMoviesFromApi()

        return if (movies.isNotEmpty()){
            repository.clearSelectedMovies("Top Rated")
            repository.insertMovies(movies.map { it.toDatabase() })
            movies
        } else{
            repository.getAllMoviesFromDatabase("Top Rated")
        }
    }
}