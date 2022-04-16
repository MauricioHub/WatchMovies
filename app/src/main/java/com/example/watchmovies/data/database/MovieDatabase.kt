package com.example.watchmovies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.watchmovies.data.database.dao.MovieDao
import com.example.watchmovies.data.database.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase(){

    abstract fun getMovieDao() : MovieDao
}