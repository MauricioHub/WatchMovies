package com.example.watchmovies.ui.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watchmovies.data.model.MovieModel
import com.example.watchmovies.databinding.ActivityMainBinding
import com.example.watchmovies.ui.view.adapters.MovieAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
    }

    private fun initRecycler(){
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.rvMovies.layoutManager = manager
        binding.rvMovies.adapter = MovieAdapter(getMovies()) { movieModel ->  onItemSelected(movieModel) }
        binding.rvMovies.addItemDecoration(decoration)
    }

    private fun onItemSelected(movieModel: MovieModel){
        Toast.makeText(this, "Soy el elemento: ${movieModel.originalTitle}", Toast.LENGTH_SHORT).show()
    }

    private fun getMovies(): MutableList<MovieModel>{
        var movieItem:MutableList<MovieModel> = ArrayList()
        movieItem.add(MovieModel(false, 0, "", "Spiderman", "",35.4567,"https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg", "1995-04-03", "", 8.9,0))
        movieItem.add(MovieModel(false, 0, "", "Daredevil", "",24.4787,"https://cursokotlin.com/wp-content/uploads/2017/07/daredevil.jpg", "1995-04-03", "", 8.9,0))
        movieItem.add(MovieModel(false, 0, "", "Wolverine", "",23.4567,"https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg", "1995-04-03", "", 8.9,0))
        movieItem.add(MovieModel(false, 0, "", "Batman", "",93.4567,"https://cursokotlin.com/wp-content/uploads/2017/07/batman.jpg", "1995-04-03", "", 8.9,0))
        movieItem.add(MovieModel(false, 0, "", "Thor", "",189.4567,"https://cursokotlin.com/wp-content/uploads/2017/07/thor.jpg", "1995-04-03", "", 8.9,0))
        movieItem.add(MovieModel(false, 0, "", "Flash", "",27.89,"https://cursokotlin.com/wp-content/uploads/2017/07/flash.png", "1995-04-03", "", 8.9,0))
        movieItem.add(MovieModel(false, 0, "", "Green Lantern", "",35.4927,"https://cursokotlin.com/wp-content/uploads/2017/07/green-lantern.jpg", "1995-04-03", "", 8.9,0))
        movieItem.add(MovieModel(false, 0, "", "Wonder Woman", "",18.2877,"https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg", "1995-04-03", "", 8.9,0))
        return movieItem
    }
}