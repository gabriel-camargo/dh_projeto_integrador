package com.gabrielcamargo.projetointegrador.moviedetails.repository

import com.gabrielcamargo.projetointegrador.data.api.NetworkUtils
import com.gabrielcamargo.projetointegrador.moviedetails.model.CastResponseModel
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