package com.example.watchmovies.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.watchmovies.databinding.ActivityMovieTrailerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieTrailerActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieTrailerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieTrailerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        val key = "h6hZkvrFIj0"
        playVideo(key)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun playVideo(key:String){
        binding.youtubePlayerView.getYouTubePlayerWhenReady(object: YouTubePlayerCallback {
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(key,0F)
            }
        } )
    }
}