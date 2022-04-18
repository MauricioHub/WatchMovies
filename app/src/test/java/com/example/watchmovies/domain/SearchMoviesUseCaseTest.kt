package com.example.watchmovies.domain

import com.example.watchmovies.data.MovieRepository
import com.example.watchmovies.domain.model.MovieItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SearchMoviesUseCaseTest{

    @RelaxedMockK
    lateinit var repository: MovieRepository

    lateinit var searchMoviesUseCase: SearchMoviesUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        searchMoviesUseCase = SearchMoviesUseCase(repository)
    }

    @Test
    fun `when TheDatabase Returns Something Then Return Values FromDatabase`() = runBlocking {
        //Given
        val myList = listOf(
            MovieItem(
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
            "")
        )
        coEvery { repository.getMoviesByNameFromDatabase("") } returns myList

        //When
        val response = searchMoviesUseCase("")

        //Then
        assert( response == myList )
    }
}