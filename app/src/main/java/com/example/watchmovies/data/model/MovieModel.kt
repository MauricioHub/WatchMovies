package com.example.watchmovies.data.model

data class MovieModel(val adult:Boolean,
                      val id:Number,
                      val originalLanguage:String,
                      val originalTitle:String,
                      val overview:String,
                      val popularity:Double,
                      val posterPath:String,
                      val releaseDate:String,
                      val title:String,
                      val voteAverage:Double,
                      val voteCount:Number)
