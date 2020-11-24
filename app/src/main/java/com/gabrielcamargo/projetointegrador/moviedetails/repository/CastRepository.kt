package com.gabrielcamargo.projetointegrador.moviedetails.repository

import android.content.Context
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.moviedetails.model.CastModel

class CastRepository(private val context: Context) {
    val mattDamon = CastModel("Matt Damon", "Mark Watney", R.drawable.img_cast)
    val cast = mutableListOf(mattDamon, mattDamon, mattDamon, mattDamon, mattDamon)


    fun getCast(callback: (cast: MutableList<CastModel>) -> Unit) {
        callback.invoke(cast)
    }
}