package com.example.watchmovies.domain.model

import com.example.watchmovies.data.model.TrailerModel

data class TrailerItem(val key: String, val name: String)

fun TrailerModel.toDomain() = TrailerItem(key, name)