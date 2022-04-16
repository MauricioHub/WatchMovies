package com.example.watchmovies.domain.model

import com.example.watchmovies.data.database.entities.MovieEntity
import com.example.watchmovies.data.model.MovieModel

data class MovieItem(
    val codeMovie: Number,
    val originalLanguage:String,
    val originalTitle:String,
    val overview:String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Number,
    var category: String
)

fun MovieModel.toDomain() = MovieItem(
    codeMovie,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath,
    releaseDate,
    title,
    voteAverage,
    voteCount,
    category
)

fun MovieEntity.toDomain() = MovieItem(
    codeMovie,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath,
    releaseDate,
    title,
    voteAverage,
    voteCount,
    category
)
