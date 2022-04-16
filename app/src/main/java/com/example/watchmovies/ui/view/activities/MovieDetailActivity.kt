package com.example.watchmovies.ui.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.watchmovies.databinding.ActivityMovieDetailBinding
import com.example.watchmovies.utils.SessionManager
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var sessionManager : SessionManager
    private var baseUrl = "https://www.themoviedb.org/movie/"

    @SuppressLint("LongLogTag")
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

        binding.tvDetailTitle.setOnClickListener {
            try {
                Log.d("RESULT>>>>>>>>>>>>>>>>>>>>>>", "resultado OOKKK.")
                startActivity(Intent(this, MovieTrailerActivity::class.java))
            } catch (exception: Exception){
                exception.stackTrace
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}