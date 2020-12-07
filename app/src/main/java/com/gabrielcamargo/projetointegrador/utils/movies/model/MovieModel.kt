package com.gabrielcamargo.projetointegrador.utils.movies.model

import com.gabrielcamargo.projetointegrador.data.api.NetworkUtils
import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("vote_average") val voteAverage: Double
){
    fun getPathPoster():String{
        return NetworkUtils.BASE_URL_IMAGE+posterPath
    }
}