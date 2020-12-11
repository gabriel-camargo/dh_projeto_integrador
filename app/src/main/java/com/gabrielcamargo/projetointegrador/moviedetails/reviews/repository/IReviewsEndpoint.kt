package com.gabrielcamargo.projetointegrador.moviedetails.reviews.repository

import com.gabrielcamargo.projetointegrador.data.api.NetworkUtils
import com.gabrielcamargo.projetointegrador.data.model.ResponseModel
import com.gabrielcamargo.projetointegrador.moviedetails.reviews.model.ReviewModel
import retrofit2.http.GET
import retrofit2.http.Path

interface IReviewsEndpoint {
    @GET("movie/{movieId}/reviews")
    suspend fun getReviews(
        @Path("movieId") movieId: Int): ResponseModel<ReviewModel>

    companion object {
        val endpoint: IReviewsEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(IReviewsEndpoint::class.java)
        }
    }
}