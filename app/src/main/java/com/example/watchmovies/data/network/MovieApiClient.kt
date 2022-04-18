package com.example.watchmovies.data.network

import com.example.watchmovies.data.model.DataMovie
import com.example.watchmovies.data.model.DataTrailer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiClient {

    @GET("3/movie/popular")
    suspend fun getFavoriteMovies(@Query("api_key") apiKey: String,
                                  @Header("Authorization") authorization:String,
                                  @Header("Content-Type") application:String = "application/json;charset=utf-8"): Response<DataMovie>

    @GET("3/movie/top_rated")
    suspend fun getRatedMovies(@Query("api_key") apiKey: String,
                               @Header("Authorization") authorization:String,
                               @Header("Content-Type") application:String = "application/json;charset=utf-8"): Response<DataMovie>

    @GET("3/movie/{movie_id}/videos?")
    suspend fun getAllTrailers(@Path(value = "movie_id", encoded = true) movieCode: String,
                               @Query("api_key") apiKey: String,
                               @Header("Authorization") authorization:String,
                               @Header("Content-Type") application:String = "application/json;charset=utf-8"): Response<DataTrailer>
}