package com.gabrielcamargo.projetointegrador.moviedetails.photos.repository

import com.gabrielcamargo.projetointegrador.data.api.NetworkUtils
import com.gabrielcamargo.projetointegrador.moviedetails.photos.model.PhotoModel
import retrofit2.http.GET
import retrofit2.http.Path

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