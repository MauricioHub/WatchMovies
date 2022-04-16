package com.example.watchmovies.data

import com.example.watchmovies.data.database.dao.MovieDao
import com.example.watchmovies.data.database.entities.MovieEntity
import com.example.watchmovies.data.network.MovieService
import com.example.watchmovies.domain.model.MovieItem
import com.example.watchmovies.domain.model.toDomain
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieService,
    private val movieDao: MovieDao){

    suspend fun getAllMoviesFromApi(category: String): List<MovieItem> {
        return when(category){
            "Favorites" -> getFavoriteMoviesFromApi()
            else -> getTopRatedMoviesFromApi()
        }
    }

    suspend fun getFavoriteMoviesFromApi(): List<MovieItem> {
        val response = api.getFavoriteMovies()
        response.map { it.category = "Favorites" }
        return response.map { it.toDomain() }
    }

    suspend fun getTopRatedMoviesFromApi(): List<MovieItem> {
        val response = api.getRatedMovies()
        response.map { it.category = "Top Rated" }
        return response.map { it.toDomain() }
    }

    suspend fun getAllMoviesFromDatabase(category: String): List<MovieItem> {
        val response = movieDao.getAllMovies(category)
        return response.map { it.toDomain() }
    }

    suspend fun insertMovies(movies: List<MovieEntity>){
        movieDao.insertAll(movies)
    }

    suspend fun clearSelectedMovies(category: String){
        movieDao.deleteSelectedMovies(category)
    }
}