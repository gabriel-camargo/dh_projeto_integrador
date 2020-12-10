package com.gabrielcamargo.projetointegrador.moviedetails.reviews.model

import com.gabrielcamargo.projetointegrador.moviedetails.details.model.AuthorDetailsModel
import com.google.gson.annotations.SerializedName

data class ReviewModel (
    @SerializedName("author_details") val authorDetails: AuthorDetailsModel,
    @SerializedName("content") val content: String
)