package com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.model

data class MovieModel(
    val name: String,
    val stars: Double,
    val genre: String,
    val year: Int,
    val img: Int
)