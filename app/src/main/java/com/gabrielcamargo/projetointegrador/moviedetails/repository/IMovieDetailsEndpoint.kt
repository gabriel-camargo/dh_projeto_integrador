package com.gabrielcamargo.projetointegrador.moviedetails.repository

import com.gabrielcamargo.projetointegrador.data.api.NetworkUtils
import com.gabrielcamargo.projetointegrador.data.model.ResponseModel
import com.gabrielcamargo.projetointegrador.utils.movies.model.MovieModel
import retrofit2.http.GET
import retrofit2.http.Path

interface IMovieDetailsEndpoint {
    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int): MovieModel


        companion object {
        const val movieId = 550
        val endpoint: IMovieDetailsEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(IMovieDetailsEndpoint::class.java)
        }
    }
}