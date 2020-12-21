package com.cgmdigitalhouse.cinelist.moviedetails.reviews.model

import com.google.gson.annotations.SerializedName

data class AuthorDetailsModel (
    @SerializedName("rating") val rating: Int,
    @SerializedName("username") val username: String
        )