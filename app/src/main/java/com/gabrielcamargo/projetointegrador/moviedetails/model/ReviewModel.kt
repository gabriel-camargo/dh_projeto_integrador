package com.gabrielcamargo.projetointegrador.moviedetails.model

import com.google.gson.annotations.SerializedName

data class ReviewModel (
    @SerializedName("author_details") val authorDetails: List<AuthorDetailsModel>,
    @SerializedName("content") val content: String
)