package com.gabrielcamargo.projetointegrador.movielistdetails.repository

import android.content.Context
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.model.MovieModel

class MovieListDetailsRepository(private val context: Context) {
    fun getMovies(callback: (movies: MutableList<MovieModel>) -> Unit) {
        callback.invoke(
            mutableListOf<MovieModel>(
                MovieModel(
                    "Filme 1 - Bla bla bla bla bla bla bla Bla bla bla bla bla bla bla",
                    8.3,
                    "Ficção científica",
                    2020,
                    R.drawable.interstellar
                ),
                MovieModel(
                    "Filme 2",
                    7.3,
                    "Terror, Drama",
                    2018,
                    R.drawable.interstellar
                ),
                MovieModel(
                    "Filme 3 - A volta",
                    8.3,
                    "Ficção científica, Drama, Fantasia",
                    2020,
                    R.drawable.interstellar
                ),
                MovieModel(
                    "Filme 4",
                    7.3,
                    "Drama",
                    2018,
                    R.drawable.interstellar
                )
            )
        )
    }
}
