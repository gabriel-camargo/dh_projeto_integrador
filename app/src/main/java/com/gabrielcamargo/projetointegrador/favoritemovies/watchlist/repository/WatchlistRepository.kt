package com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.repository

import android.content.Context
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.model.MovieModel

class WatchlistRepository(private val context: Context) {
    fun getWatchlistMovies(callback: (movies: MutableList<MovieModel>) -> Unit) {
        callback.invoke(
                mutableListOf<MovieModel>(
                        MovieModel(
                                "Interestelar",
                                8.6,
                                "Ficção científica, Aventura, Drama",
                                2014,
                                R.drawable.interstellar
                        ),
                        MovieModel(
                                "Invocação do Mal",
                                7.5,
                                "Terror, Mistério",
                                2013,
                                R.drawable.invocacao
                        ),
                        MovieModel(
                                "Vingadores - Endgame",
                                8.4,
                                "Ação, Aventura",
                                2019,
                                R.drawable.vingadores
                        ),
                        MovieModel(
                                "Interestelar",
                                8.6,
                                "Ficção científica, Aventura, Drama",
                                2014,
                                R.drawable.interstellar
                        ),
                        MovieModel(
                                "Invocação do Mal",
                                7.5,
                                "Terror, Mistério",
                                2013,
                                R.drawable.invocacao
                        ),
                        MovieModel(
                                "Vingadores - Endgame",
                                8.4,
                                "Ação, Aventura",
                                2019,
                                R.drawable.vingadores
                        ),
                )
        )
    }

}
