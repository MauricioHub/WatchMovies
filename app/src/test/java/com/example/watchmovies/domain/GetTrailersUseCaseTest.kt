package com.example.watchmovies.domain

import com.example.watchmovies.data.MovieRepository
import com.example.watchmovies.domain.model.TrailerItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetTrailersUseCaseTest{

    @RelaxedMockK
    lateinit var repository: MovieRepository

    lateinit var getTrailersUseCase: GetTrailersUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getTrailersUseCase = GetTrailersUseCase(repository)
    }

    @Test
    fun `when TheApi Doesnt Return Anything Then Return emptyList`() = runBlocking {
        //Given
        val myList = listOf<TrailerItem>()
        coEvery { repository.getAllTrailersFromApi("", "") } returns myList

        //When
        val response = getTrailersUseCase("", "")

        //Then
        assert( response == emptyList<TrailerItem>())
    }

    @Test
    fun `when TheApi Returns Something Then Returns FromApi`() = runBlocking {
        //Given
        val myList = listOf(
            TrailerItem("WtO3CsleMDg", "Official Trailer: Braveheart (1995)"),
            TrailerItem("nMft5QDOHek", "Braveheart - Trailer"))
        coEvery { repository.getAllTrailersFromApi("", "") } returns myList

        //When
        val response = getTrailersUseCase("", "")

        //Then
        assert( response == myList )
    }
}