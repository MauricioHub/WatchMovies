package com.example.watchmovies.ui.view.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watchmovies.data.model.CategoryModel
import com.example.watchmovies.data.model.MovieModel
import com.example.watchmovies.databinding.ActivityMainBinding
import com.example.watchmovies.domain.model.MovieItem
import com.example.watchmovies.ui.view.adapters.CategoryAdapter
import com.example.watchmovies.ui.view.adapters.MovieAdapter
import com.example.watchmovies.ui.viewmodel.MovieViewModel
import com.example.watchmovies.utils.SessionManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sessionManager : SessionManager
    private val initialCategory = "Favorites"

    private val movieViewModel : MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCategoryRecycler()
        setupCategoryRecycler(getCategoriesLst())
        initRecycler()
        sessionManager = SessionManager(this)

        movieViewModel.fetchMoviesByCategory(initialCategory)

        movieViewModel.allMoviesLst.observe(this, Observer{ moviesLst ->
            setupRecycler(moviesLst)
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

    private fun initCategoryRecycler(){
        binding.rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.setHasFixedSize(true)
    }

    private fun setupRecycler(favoriteMovies: List<MovieItem>){
        binding.rvMovies.adapter = MovieAdapter(favoriteMovies) { movieModel ->  onItemSelected(movieModel) }
    }

    private fun setupCategoryRecycler(categoryModelLst: List<CategoryModel>){
        binding.rvCategories.adapter = CategoryAdapter(categoryModelLst) { categoryModel ->  onCategorySelected(categoryModel) }
    }

    private fun onItemSelected(movieModel: MovieItem){
        sessionManager.saveMovieItem(movieModel)
        startActivity(Intent(this, MovieDetailActivity::class.java));
    }

    private fun onCategorySelected(categoryModel: CategoryModel){
        movieViewModel.fetchMoviesByCategory(categoryModel.name)
    }

    private fun getCategoriesLst(): MutableList<CategoryModel>{
        var categoriesLst:MutableList<CategoryModel> = ArrayList()
        categoriesLst.add(CategoryModel("Favorites"))
        categoriesLst.add(CategoryModel("Top Rated"))
        return categoriesLst
    }
}