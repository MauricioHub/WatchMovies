package com.example.watchmovies.ui.view.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.watchmovies.data.model.MovieModel
import com.example.watchmovies.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MovieHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemMovieBinding.bind(view)
    private val baseURL = "https://image.tmdb.org/t/p/w500/"

    fun render(movie: MovieModel, onClickListener:(MovieModel) -> Unit){
        binding.tvTitle.text = movie.originalTitle
        binding.tvRating.text = movie.voteAverage.toString()
        binding.tvPopularity.text = movie.popularity.toString()
        binding.tvReleaseDate.text = movie.releaseDate
        Picasso.get().load(baseURL + movie.posterPath).into(binding.ivPoster)

        itemView.setOnClickListener { onClickListener(movie) }
    }
}