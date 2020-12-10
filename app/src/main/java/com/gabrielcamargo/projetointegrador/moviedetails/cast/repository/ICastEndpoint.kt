package com.gabrielcamargo.projetointegrador.moviedetails.cast.repository

import com.gabrielcamargo.projetointegrador.data.api.NetworkUtils
import com.gabrielcamargo.projetointegrador.moviedetails.cast.model.CastResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ICastEndpoint {
    @GET("movie/{movieId}/credits")
    suspend fun getCast(
        @Path("movieId") movieId: Int): CastResponseModel

    companion object {
        val endpoint: ICastEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(ICastEndpoint::class.java)
        }
    }
}