package com.example.watchmovies.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.watchmovies.R
import com.example.watchmovies.domain.model.MovieItem
import com.example.watchmovies.domain.model.TrailerItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SessionManager (context : Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),
        Context.MODE_PRIVATE)

    companion object {
        const val MOVIE_ITEM= "movie_item"
        const val TRAILER_ITEM= "trailer_item"
    }

    fun saveMovieItem(movie: MovieItem){
        val gson = Gson()
        val jsonText = gson.toJson(movie)

        val editor = prefs.edit()
        editor.putString(MOVIE_ITEM, jsonText)
        editor.apply()
    }

    fun fetchMovieItem() : MovieItem {
        val gson = Gson()
        val jsonText: String? = prefs.getString(MOVIE_ITEM, null)
        val itemType = object : TypeToken<MovieItem>() {}.type

        return gson.fromJson(jsonText, itemType)
    }

    fun saveTrailerItem(trailer: TrailerItem){
        val gson = Gson()
        val jsonText = gson.toJson(trailer)

        val editor = prefs.edit()
        editor.putString(TRAILER_ITEM, jsonText)
        editor.apply()
    }

    fun fetchTrailerItem() : TrailerItem {
        val gson = Gson()
        val jsonText: String? = prefs.getString(TRAILER_ITEM, null)
        val itemType = object : TypeToken<TrailerItem>() {}.type

        return gson.fromJson(jsonText, itemType)
    }
}