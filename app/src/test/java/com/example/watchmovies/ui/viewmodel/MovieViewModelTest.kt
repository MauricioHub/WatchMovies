package com.example.watchmovies.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.watchmovies.domain.GetMoviesUseCase
import com.example.watchmovies.domain.GetTrailersUseCase
import com.example.watchmovies.domain.SearchMoviesUseCase
import com.example.watchmovies.domain.model.MovieItem
import com.example.watchmovies.domain.model.TrailerItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest{

    @RelaxedMockK
    lateinit var getMoviesUseCase: GetMoviesUseCase

    @RelaxedMockK
    lateinit var getTrailersUseCase: GetTrailersUseCase

    @RelaxedMockK
    lateinit var searchMoviesUseCase: SearchMoviesUseCase

    lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        movieViewModel = MovieViewModel(getMoviesUseCase, getTrailersUseCase, searchMoviesUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when moviesUseCase returns allMovies Set on The livedata`() = runTest {
        //Given
        val myList = listOf(MovieItem(
                    197 ,
                    "en",
                    "Braveheart",
                    "Enraged at the slaughter of Murron, his new bride and childhood love, " +
                            "Scottish warrior William Wallace slays a platoon of the local English " +
                            "lord's soldiers. This leads the village to revolt and, eventually, the " +
                            "entire country to rise up against English rule.",
                    67.73,
                    "/or1gBugydmjToAEq7OZY0owwFk.jpg",
                    "1995-03-14",
                    "Braveheart",
                    7.9,
                    82.88,
                    ""))
        coEvery { getMoviesUseCase("") } returns myList

        //When
        movieViewModel.fetchMoviesByCategory("")

        //Then
        assert( movieViewModel.allMoviesLst.value == myList )
    }

    @Test
    fun `when trailersUseCase returns allTrailers Set on The livedata`() = runTest {
        //Given
        val myList = listOf(
            TrailerItem("WtO3CsleMDg", "Official Trailer: Braveheart (1995)"),
            TrailerItem("nMft5QDOHek", "Braveheart - Trailer")
        )
        coEvery { getTrailersUseCase("", "") } returns myList

        //When
        movieViewModel.fetchAllTrailers("", "")

        //Then
        assert( movieViewModel.allTrailersLst.value == myList )
    }

    @Test
    fun `when searchMoviesUseCase returns allFoundMovies Set on The livedata`() = runTest {
        //Given
        val myList = listOf(MovieItem(
            197 ,
            "en",
            "Braveheart",
            "Enraged at the slaughter of Murron, his new bride and childhood love, " +
                    "Scottish warrior William Wallace slays a platoon of the local English " +
                    "lord's soldiers. This leads the village to revolt and, eventually, the " +
                    "entire country to rise up against English rule.",
            67.73,
            "/or1gBugydmjToAEq7OZY0owwFk.jpg",
            "1995-03-14",
            "Braveheart",
            7.9,
            82.88,
            ""))
        coEvery { searchMoviesUseCase("") } returns myList

        //When
        movieViewModel.fetchMoviesByName("")

        //Then
        assert( movieViewModel.foundMoviesLst.value == myList )
    }
}