package com.example.watchmovies.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.watchmovies.data.database.entities.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table WHERE category = :category " +
            "ORDER BY original_title ASC")
    suspend fun getAllMovies(category: String): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("DELETE FROM movie_table WHERE category = :category")
    suspend fun deleteSelectedMovies(category: String)
}