package com.example.watchmovies.data.network

import com.example.watchmovies.data.model.DataMovie
import com.example.watchmovies.data.model.DataTrailer
import com.example.watchmovies.data.model.MovieModel
import com.example.watchmovies.data.model.TrailerModel
import com.example.watchmovies.utils.ConstantsUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class MovieService @Inject constructor(
    private val api: MovieApiClient){

    suspend fun getFavoriteMovies(): List<MovieModel>{
        return withContext(Dispatchers.IO){
            try {
                val response : Response<DataMovie> = api.getFavoriteMovies("Bearer ${ConstantsUtils.BEARER_TOKEN}")
                response.body()?.results ?: emptyList()
            } catch (exception: Exception){
                throw Exception()
            }
        }
    }

    suspend fun getRatedMovies(): List<MovieModel>{
        return withContext(Dispatchers.IO){
            try {
                val response : Response<DataMovie> = api.getRatedMovies("Bearer ${ConstantsUtils.BEARER_TOKEN}")
                response.body()?.results ?: emptyList()
            } catch (exception: Exception){
                throw Exception()
            }
        }
    }

    suspend fun getAllTrailers(codeMovie: String, apiKey: String): List<TrailerModel>{
        return withContext(Dispatchers.IO){
            try{
                val response : Response<DataTrailer> =
                    api.getAllTrailers(codeMovie, apiKey,"Bearer $${ConstantsUtils.BEARER_TOKEN}")
                response.body()?.results ?: emptyList()
            } catch (exception: Exception){
                throw Exception()
            }
        }
    }
}