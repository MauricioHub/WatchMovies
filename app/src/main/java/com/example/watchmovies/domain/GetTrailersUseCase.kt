package com.example.watchmovies.domain

import com.example.watchmovies.data.MovieRepository
import com.example.watchmovies.domain.model.TrailerItem
import javax.inject.Inject

class GetTrailersUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(codeMovie: String, apiKey: String) : List<TrailerItem>{
        return try {
            val trailersLst = repository.getAllTrailersFromApi(codeMovie, apiKey)

            if(!trailersLst.isNullOrEmpty())
                trailersLst
            else
                emptyList()
        } catch (exception: Exception){
            emptyList()
        }
    }
}