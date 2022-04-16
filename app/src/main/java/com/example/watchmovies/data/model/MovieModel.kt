package com.example.watchmovies.data.model

import com.google.gson.annotations.SerializedName

data class MovieModel(val adult:Boolean,
                      @SerializedName("id") val codeMovie:Number,
                      @SerializedName("original_language") val originalLanguage:String,
                      @SerializedName("original_title") val originalTitle:String,
                      val overview:String,
                      val popularity:Double,
                      @SerializedName("poster_path") val posterPath:String,
                      @SerializedName("release_date") val releaseDate:String,
                      val title:String,
                      @SerializedName("vote_average") val voteAverage:Double,
                      @SerializedName("vote_count") val voteCount:Number,
                      var category:String = "")
