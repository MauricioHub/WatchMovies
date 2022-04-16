package com.example.watchmovies.ui.view.activities

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.watchmovies.R
import com.example.watchmovies.databinding.ActivityMovieDetailBinding
import com.example.watchmovies.utils.SessionManager
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity(R.layout.activity_movie_detail) {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var sessionManager : SessionManager
    private var baseUrl = "https://www.themoviedb.org/movie/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionManager = SessionManager(this)
        val movieItem = sessionManager.fetchMovieItem()

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        binding.tvDetailTitle.text = movieItem.originalTitle
        binding.tvDetailDescription.text = movieItem.overview
        binding.wvMovie.loadUrl(baseUrl + movieItem.codeMovie + movieItem.originalTitle)

        binding.wvMovie.settings.javaScriptEnabled = true;
        binding.wvMovie.settings.domStorageEnabled = true;
        binding.wvMovie.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}