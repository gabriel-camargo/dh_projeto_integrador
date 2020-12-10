package com.gabrielcamargo.projetointegrador.moviedetails.model

import com.google.gson.annotations.SerializedName


data class PhotoModel (
    @SerializedName("id") val id: Int,
    @SerializedName("posters") val posters: List<PosterModel>
)