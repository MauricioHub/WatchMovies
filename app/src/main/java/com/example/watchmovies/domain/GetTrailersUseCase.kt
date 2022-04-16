package com.example.watchmovies.domain

import com.example.watchmovies.data.MovieRepository
import javax.inject.Inject

class GetTrailersUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(codeMovie: String, apiKey: String) =
        repository.getAllTrailersFromApi(codeMovie, apiKey)
}