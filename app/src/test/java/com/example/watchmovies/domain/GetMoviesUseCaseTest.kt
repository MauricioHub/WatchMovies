package com.example.watchmovies.domain

import com.example.watchmovies.data.MovieRepository
import com.example.watchmovies.domain.model.MovieItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMoviesUseCaseTest{

    @RelaxedMockK
    lateinit var repository: MovieRepository

    lateinit var getMoviesUseCase: GetMoviesUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getMoviesUseCase = GetMoviesUseCase(repository)
    }

    @Test
    fun `when TheApi Doesnt Return Anything Then Return Values FromDatabase`() = runBlocking {
        //Given
        coEvery { repository.getAllMoviesFromApi("") } returns emptyList()

        //When
        getMoviesUseCase("")

        //Then
        coVerify(exactly = 1) { repository.getAllMoviesFromDatabase("") }
    }

    @Test
    fun `when TheApi Returns Something Then Return Values FromApi`() = runBlocking {
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
        coEvery { repository.getAllMoviesFromApi("") } returns myList

        //When
        val response = getMoviesUseCase("")

        //Then
        coVerify(exactly = 1) { repository.clearSelectedMovies("") }
        coVerify(exactly = 1) { repository.insertMovies( any() ) }
        coVerify(exactly = 0) { repository.getAllMoviesFromDatabase("") }
        assert( response == myList )
    }
}