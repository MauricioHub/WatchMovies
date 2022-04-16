package com.example.watchmovies.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.watchmovies.R
import com.example.watchmovies.data.model.MovieModel
import com.example.watchmovies.domain.model.MovieItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SessionManager (context : Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),
        Context.MODE_PRIVATE)

    companion object {
        const val MOVIES_LIST= "movies_list"
        const val MOVIE_ITEM= "movie_item"
    }

    fun saveMoviesList(moviesLst: List<MovieItem>){
        val gson = Gson()
        val jsonText = gson.toJson(moviesLst)

        val editor = prefs.edit()
        editor.putString(MOVIES_LIST, jsonText)
        editor.apply()
    }

    fun fetchMoviesList() : List<MovieItem> {
        val gson = Gson()
        val jsonText: String? = prefs.getString(MOVIES_LIST, null)
        val itemType = object : TypeToken<ArrayList<MovieItem>>() {}.type

        return gson.fromJson<ArrayList<MovieItem>>(jsonText, itemType)
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
}