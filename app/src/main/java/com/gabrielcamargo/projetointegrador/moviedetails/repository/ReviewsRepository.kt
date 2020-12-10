package com.gabrielcamargo.projetointegrador.moviedetails.repository

import android.content.Context
import com.gabrielcamargo.projetointegrador.moviedetails.model.ReviewModel

class ReviewsRepository{
    private val client = IReviewsEndpoint.endpoint

    suspend fun getReviews(id: Int) = client.getReviews(id)
    suspend fun getReviews2(id: Int) = client.getReviews2(id)

}