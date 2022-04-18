package com.example.watchmovies.data

import com.example.watchmovies.data.database.dao.MovieDao
import com.example.watchmovies.data.database.entities.MovieEntity
import com.example.watchmovies.data.network.MovieService
import com.example.watchmovies.domain.model.MovieItem
import com.example.watchmovies.domain.model.TrailerItem
import com.example.watchmovies.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieService,
    private val movieDao: MovieDao){

    suspend fun getAllMoviesFromApi(category: String): List<MovieItem> {
        return when(category){
            "Popular" -> getFavoriteMoviesFromApi()
            else -> getTopRatedMoviesFromApi()
        }
    }

    private suspend fun getFavoriteMoviesFromApi(): List<MovieItem> {
        val response = api.getFavoriteMovies()
        response.map { it.category = "Popular" }
        return response.map { it.toDomain() }
    }

    private suspend fun getTopRatedMoviesFromApi(): List<MovieItem> {
        val response = api.getRatedMovies()
        response.map { it.category = "Top Rated" }
        return response.map { it.toDomain() }
    }

    suspend fun getAllTrailersFromApi(codeMovie: String, apiKey: String): List<TrailerItem>{
        val response = api.getAllTrailers(codeMovie, apiKey)
        return response.map { it.toDomain() }
    }

    suspend fun getAllMoviesFromDatabase(category: String): List<MovieItem> {
        val response = movieDao.getAllMovies(category)
        return response.map { it.toDomain() }
    }

    suspend fun getMoviesByNameFromDatabase(name: String): List<MovieItem> {
        return withContext(Dispatchers.IO){
            val response = movieDao.getAllMoviesByName(name)
            response.map { it.toDomain() }
        }
    }

    suspend fun insertMovies(movies: List<MovieEntity>){
        movieDao.insertAll(movies)
    }

    suspend fun clearSelectedMovies(category: String){
        movieDao.deleteSelectedMovies(category)
    }
}