package com.gabrielcamargo.projetointegrador.moviedetails.repository

import com.gabrielcamargo.projetointegrador.data.api.NetworkUtils
import com.gabrielcamargo.projetointegrador.moviedetails.model.PhotoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IPhotosEndpoint {
    @GET("movie/{movieId}/images")
    suspend fun getPhotos(
        @Path("movieId") movieId: Int): PhotoModel

    companion object {
        val endpoint: IPhotosEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(IPhotosEndpoint::class.java)
        }
    }
}