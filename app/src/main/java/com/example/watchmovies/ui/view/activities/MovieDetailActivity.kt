package com.example.watchmovies.ui.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watchmovies.databinding.ActivityMovieDetailBinding
import com.example.watchmovies.domain.model.TrailerItem
import com.example.watchmovies.ui.view.adapters.TrailerAdapter
import com.example.watchmovies.ui.viewmodel.MovieViewModel
import com.example.watchmovies.utils.SessionManager
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var sessionManager : SessionManager
    private val baseURL = "https://image.tmdb.org/t/p/w500/"
    private val apiKey = "d8e8e840d1dd7a4e713bff5822ae7a42"
    private val synopsis = "SYNOPSIS: "

    private val movieViewModel : MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionManager = SessionManager(this)
        val movieItem = sessionManager.fetchMovieItem()

        initTrailerRecycler()
        movieViewModel.fetchAllTrailers(movieItem.codeMovie.toString(), apiKey)

        movieViewModel.allTrailersLst.observe(this, Observer{ trailersLst ->
            setupTrailerRecycler(trailersLst)
        })

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.tvDetailDescription.text = synopsis + movieItem.overview
        Picasso.get().load(baseURL + movieItem.posterPath).into(binding.ivDetailPoster)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initTrailerRecycler(){
        binding.rvTrailers.layoutManager = LinearLayoutManager(this)
        binding.rvTrailers.setHasFixedSize(true)
    }

    private fun setupTrailerRecycler(trailerItemLst: List<TrailerItem>){
        binding.rvTrailers.adapter = TrailerAdapter(trailerItemLst) { trailerItem ->  onTrailerSelected(trailerItem) }
    }

    private fun onTrailerSelected(trailerItem: TrailerItem){
        sessionManager.saveTrailerItem(trailerItem)
        startActivity(Intent(this, MovieTrailerActivity::class.java))
    }
}