package com.gabrielcamargo.projetointegrador.moviedetails.reviews.repository

class ReviewsRepository{
    private val client = IReviewsEndpoint.endpoint

    suspend fun getReviews(id: Int) = client.getReviews(id)
    suspend fun getReviews2(id: Int) = client.getReviews2(id)

}