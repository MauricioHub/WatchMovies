package com.example.watchmovies.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.watchmovies.R
import com.example.watchmovies.domain.model.MovieItem
import com.example.watchmovies.ui.view.holders.MovieHolder

class MovieAdapter(private val moviesLst:List<MovieItem>,
                   private val onClickListener:(MovieItem) -> Unit): RecyclerView.Adapter<MovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieHolder(layoutInflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.render(moviesLst[position], onClickListener)
    }

    override fun getItemCount(): Int = moviesLst.size
}