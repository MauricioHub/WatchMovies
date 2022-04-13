package com.example.watchmovies.data.network

import com.example.watchmovies.data.model.DataMovie
import com.example.watchmovies.data.model.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MovieService @Inject constructor(
    private val api: MovieApiClient){

    private val bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE2NDkxNzA2OTIsImF1ZCI6ImQ4ZThlODQwZDFkZDdhNGU3MTNiZmY1ODIyYWU3YTQyIiwianRpIjoiNDIzNjY5NiIsInN1YiI6IjYyNGI0ZjQ0YmRjMzRjMDA0ZTY5NDU5MyIsInZlcnNpb24iOjEsInNjb3BlcyI6WyJhcGlfcmVhZCIsImFwaV93cml0ZSJdfQ.e3Q9rxCrrtzAzRs2pFlrK1AeE_eW39PB_qTQ9Nn15Yk"

    suspend fun getFavoriteMovies(): List<MovieModel>{
        return withContext(Dispatchers.IO){
            val response : Response<DataMovie> = api.getFavoriteMovies("Bearer $bearerToken")
            response.body()?.results ?: emptyList()
        }
    }

    suspend fun getRatedMovies(): List<MovieModel>{
        return withContext(Dispatchers.IO){
            val response : Response<DataMovie> = api.getRatedMovies("Bearer $bearerToken")
            response.body()?.results ?: emptyList()
        }
    }
}