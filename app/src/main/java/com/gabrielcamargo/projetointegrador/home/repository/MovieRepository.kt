package com.gabrielcamargo.projetointegrador.home.repository

class MovieRepository {
    private val client = IMovieEndpoint.endpoint

    suspend fun getMoviesPopular() = client.getMoviesPopular()

    suspend fun getMoviesNowPaying() = client.getMoviesNowPaying()
}