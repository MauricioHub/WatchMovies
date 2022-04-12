package com.example.watchmovies.data.model

import com.google.gson.annotations.SerializedName

data class DataMovie(val results:List<MovieModel>,
                     @SerializedName("total_pages") val totalPages:Number,
                     @SerializedName("total_results") val totalResults:Number)