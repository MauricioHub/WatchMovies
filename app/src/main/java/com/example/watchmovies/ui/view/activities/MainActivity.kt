package com.example.watchmovies.ui.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watchmovies.data.model.CategoryModel
import com.example.watchmovies.databinding.ActivityMainBinding
import com.example.watchmovies.domain.model.MovieItem
import com.example.watchmovies.ui.view.adapters.CategoryAdapter
import com.example.watchmovies.ui.view.adapters.MovieAdapter
import com.example.watchmovies.ui.viewmodel.MovieViewModel
import com.example.watchmovies.utils.ConstantsUtils
import com.example.watchmovies.utils.SessionManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sessionManager : SessionManager

    private val movieViewModel : MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCategoryRecycler()
        setupCategoryRecycler(getCategoriesLst())
        initRecycler()
        sessionManager = SessionManager(this)
        setupSearchView(binding.svSearchMovie)

        movieViewModel.fetchMoviesByCategory(ConstantsUtils.INITIAL_CATEGORY)

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
        startActivity(Intent(this, MovieDetailActivity::class.java))
    }

    private fun onCategorySelected(categoryModel: CategoryModel){
        movieViewModel.fetchMoviesByCategory(categoryModel.name)
    }

    private fun getCategoriesLst(): MutableList<CategoryModel>{
        var categoriesLst:MutableList<CategoryModel> = ArrayList()
        categoriesLst.add(CategoryModel("Popular"))
        categoriesLst.add(CategoryModel("Top Rated"))
        return categoriesLst
    }

    private fun setUpNameObserver(name: String) {
        movieViewModel.fetchMoviesByName(name)
    }

    private fun setupObserver() {
        movieViewModel.foundMoviesLst.observe(this, Observer{ moviesLst ->
            setupRecycler(moviesLst)
        })
    }

    private fun setupSearchView(view: SearchView) {
        view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(name: String): Boolean {
                setUpNameObserver(name)
                setupObserver()
                return false
            }

            override fun onQueryTextChange(name: String): Boolean {
                setUpNameObserver(name)
                setupObserver()
                return false
            }
        })
    }
}