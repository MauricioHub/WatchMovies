package com.example.watchmovies.ui.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.watchmovies.databinding.ActivitySplashBinding
import com.example.watchmovies.ui.animations.AnimationManager
import com.example.watchmovies.ui.viewmodel.SplashViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.lang.Exception

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val animator = AnimationManager()
        animator.totalAnimations(binding.splashScreenImage)

        lifecycleScope.launch {
            splashViewModel.isLoading.catch {}
                .collect { it ->
                    if (!it) {
                        throwMainActivity()
                    }
                }
        }
    }

    private fun throwMainActivity(){
        try {
            startActivity(Intent(this, MainActivity::class.java))
        } catch (ex: Exception){
            ex.stackTrace
        }
    }
}