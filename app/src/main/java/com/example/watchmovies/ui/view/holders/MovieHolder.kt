package com.example.watchmovies.ui.view.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.watchmovies.databinding.ItemMovieBinding
import com.example.watchmovies.domain.model.MovieItem
import com.example.watchmovies.utils.ConstantsUtils
import com.squareup.picasso.Picasso

class MovieHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemMovieBinding.bind(view)

    fun render(movie: MovieItem, onClickListener:(MovieItem) -> Unit){
        binding.tvTitle.text = movie.originalTitle
        binding.tvRating.text = movie.voteAverage.toString()
        binding.tvPopularity.text = movie.popularity.toString()
        binding.tvReleaseDate.text = movie.releaseDate
        Picasso.get().load(ConstantsUtils.BASE_IMAGE_URL + movie.posterPath)
            .into(binding.ivPoster)

        itemView.setOnClickListener { onClickListener(movie) }
    }
}