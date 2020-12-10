package com.gabrielcamargo.projetointegrador.moviedetails.details.repository

import com.gabrielcamargo.projetointegrador.data.api.NetworkUtils
import com.gabrielcamargo.projetointegrador.utils.movies.model.MovieModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IMovieDetailsEndpoint {
    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("language") language: String = "pt-BR"
    ): MovieModel


        companion object {
        const val movieId = 550
        val endpoint: IMovieDetailsEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(IMovieDetailsEndpoint::class.java)
        }
    }
}