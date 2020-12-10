package com.gabrielcamargo.projetointegrador.moviedetails.cast.model

import com.gabrielcamargo.projetointegrador.data.api.NetworkUtils
import com.google.gson.annotations.SerializedName

data class CastModel (
    @SerializedName("name") val name: String,
    @SerializedName("character") val character: String,
    @SerializedName("profile_path") val profilePath: String
){
    fun getPathImage():String{
        return NetworkUtils.BASE_URL_IMAGE+profilePath
    }
}