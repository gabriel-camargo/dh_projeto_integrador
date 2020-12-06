package com.gabrielcamargo.projetointegrador.search.repository

import com.gabrielcamargo.projetointegrador.data.api.NetworkUtils
import com.gabrielcamargo.projetointegrador.data.model.ResponseModel
import com.gabrielcamargo.projetointegrador.utils.movies.model.MovieModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ISearchEndpoint {
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): ResponseModel<MovieModel>

    companion object {
        val endpoint: ISearchEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(ISearchEndpoint::class.java)
        }
    }
}