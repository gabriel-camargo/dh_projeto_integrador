package com.gabrielcamargo.projetointegrador.moviedetails.repository

import android.content.Context
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.moviedetails.model.CastModel
import com.gabrielcamargo.projetointegrador.moviedetails.model.MovieDetailsModel

class MovieDetailsRepository(private val context: Context) {
    val perdidoEmMarte = MovieDetailsModel(R.drawable.photo1, "Perdido em Marte", 8.0, "Ficção científica", 2015, "2h 31min")

    fun getMovieDetails(callback: (movieDetails: MovieDetailsModel) -> Unit) {
        callback.invoke(perdidoEmMarte)
    }
}