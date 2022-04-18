package com.example.watchmovies.domain

import com.example.watchmovies.data.MovieRepository
import com.example.watchmovies.data.database.entities.toDatabase
import com.example.watchmovies.domain.model.MovieItem
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(category: String): List<MovieItem>{
        return try {
            val moviesLst = repository.getAllMoviesFromApi(category)
            if (moviesLst.isNotEmpty()){
                repository.clearSelectedMovies(category)
                repository.insertMovies(moviesLst.map { it.toDatabase() })
                moviesLst
            } else{
                repository.getAllMoviesFromDatabase(category)
            }
        } catch (exception: Exception){
            repository.getAllMoviesFromDatabase(category)
        }
    }
}