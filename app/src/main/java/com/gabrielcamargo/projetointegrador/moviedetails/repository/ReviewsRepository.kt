package com.gabrielcamargo.projetointegrador.moviedetails.repository

import android.content.Context
import com.gabrielcamargo.projetointegrador.moviedetails.model.ReviewModel

class ReviewsRepository(private val context: Context) {
    val review1 = ReviewModel("Filme muito bom", 8.0)
    val reviews = mutableListOf(review1, review1, review1, review1)

    fun getReviews(callback: (reviews: MutableList<ReviewModel>) -> Unit) {
        callback.invoke(reviews)
    }
}