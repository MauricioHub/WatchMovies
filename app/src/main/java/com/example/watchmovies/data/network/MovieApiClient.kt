package com.example.watchmovies.data.network

import com.example.watchmovies.data.model.DataMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface MovieApiClient {

    @GET("account/624b4f44bdc34c004e694593/movie/favorites")
    suspend fun getFavoriteMovies(@Header("Authorization") authorization:String,
                                  @Header("Content-Type") application:String = "application/json;charset=utf-8"): Response<DataMovie>
}