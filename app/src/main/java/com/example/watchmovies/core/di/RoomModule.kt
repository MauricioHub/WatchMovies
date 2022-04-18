package com.example.watchmovies.core.di

import android.content.Context
import androidx.room.Room
import com.example.watchmovies.data.database.MovieDatabase
import com.example.watchmovies.utils.ConstantsUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context)
            = Room.databaseBuilder(context, MovieDatabase::class.java,
        ConstantsUtils.MOVIE_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideMovieDao(db: MovieDatabase) = db.getMovieDao()
}