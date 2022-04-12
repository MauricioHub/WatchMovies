package com.example.watchmovies.ui.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watchmovies.data.model.MovieModel
import com.example.watchmovies.databinding.ActivityMainBinding
import com.example.watchmovies.ui.view.adapters.MovieAdapter
import com.example.watchmovies.ui.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val movieViewModel : MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()

        movieViewModel.onCreate()

        movieViewModel.favoriteMovies.observe(this, Observer{ favoriteLst ->
            setupRecycler(favoriteLst)
        })

        movieViewModel.loading.observe(this, Observer {
            binding.loading.isVisible = it
        })
    }

    private fun initRecycler(){
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.rvMovies.layoutManager = manager
        binding.rvMovies.addItemDecoration(decoration)
    }

    private fun setupRecycler(favoriteMovies: List<MovieModel>){
        binding.rvMovies.adapter = MovieAdapter(favoriteMovies) { movieModel ->  onItemSelected(movieModel) }
    }

    private fun onItemSelected(movieModel: MovieModel){
        Toast.makeText(this, "Soy el elemento: ${movieModel.originalTitle}", Toast.LENGTH_SHORT).show()
    }
}