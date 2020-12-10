package com.gabrielcamargo.projetointegrador.moviedetails.repository

import com.gabrielcamargo.projetointegrador.data.api.NetworkUtils
import com.gabrielcamargo.projetointegrador.data.model.ResponseModel
import com.gabrielcamargo.projetointegrador.moviedetails.model.ReviewModel
import retrofit2.http.GET
import retrofit2.http.Path

interface IReviewsEndpoint {
    @GET("movie/{movieId}/reviews")
    suspend fun getReviews(
        @Path("movieId") movieId: Int): ResponseModel<ReviewModel>
    @GET("movie/{movieId}/reviews")
    suspend fun getReviews2(
        @Path("movieId") movieId: Int): Any

    companion object {
        val endpoint: IReviewsEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(IReviewsEndpoint::class.java)
        }
    }
}