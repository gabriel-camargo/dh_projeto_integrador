package com.gabrielcamargo.projetointegrador.favoritemovies.movielist.repository

import android.content.Context
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.favoritemovies.movielist.model.MovieListModel

class MovieListRepository(private val context: Context) {
    fun getMovieLists(callback: (movieLists: MutableList<MovieListModel>) -> Unit) {
        callback.invoke(
            mutableListOf<MovieListModel>(
                MovieListModel(
                    "Meus filmes favoritos",
                    10,
                    R.drawable.perdidomarte
                ),
                MovieListModel(
                    "Lista 2",
                    20,
                    R.drawable.corra
                ),
                MovieListModel(
                    "Lista 3",
                    13,
                    R.drawable.sonic
                ),
                MovieListModel(
                    "Lista 4",
                    10,
                    R.drawable.perdidomarte
                ),
                MovieListModel(
                    "Lista 5",
                    20,
                    R.drawable.corra
                ),
                MovieListModel(
                    "Lista 6",
                    13,
                    R.drawable.sonic
                ),
            )
        )
    }
}
