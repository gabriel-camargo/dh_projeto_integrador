package com.gabrielcamargo.projetointegrador.moviedetails.model

import com.google.gson.annotations.SerializedName

data class AuthorDetailsModel (
    @SerializedName("rating") val rating: Int
        )