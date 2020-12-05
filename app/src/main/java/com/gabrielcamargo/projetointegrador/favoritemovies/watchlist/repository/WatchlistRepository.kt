package com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.repository

import android.content.Context
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.utils.moviesoffline.model.MovieModelOffline

class WatchlistRepository(private val context: Context) {
    fun getWatchlistMovies(callback: (movies: MutableList<MovieModelOffline>) -> Unit) {
        callback.invoke(
                mutableListOf<MovieModelOffline>(
                        MovieModelOffline(
                                "Interestelar",
                                8.6,
                                "Ficção científica, Aventura, Drama",
                                2014,
                                R.drawable.interstellar
                        ),
                        MovieModelOffline(
                                "Invocação do Mal",
                                7.5,
                                "Terror, Mistério",
                                2013,
                                R.drawable.invocacao
                        ),
                        MovieModelOffline(
                                "Vingadores - Endgame",
                                8.4,
                                "Ação, Aventura",
                                2019,
                                R.drawable.vingadores
                        ),
                        MovieModelOffline(
                                "Interestelar",
                                8.6,
                                "Ficção científica, Aventura, Drama",
                                2014,
                                R.drawable.interstellar
                        ),
                        MovieModelOffline(
                                "Invocação do Mal",
                                7.5,
                                "Terror, Mistério",
                                2013,
                                R.drawable.invocacao
                        ),
                        MovieModelOffline(
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
