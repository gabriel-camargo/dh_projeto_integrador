package com.gabrielcamargo.projetointegrador.favoritemovies.movielist.repository

import android.content.Context
import com.gabrielcamargo.projetointegrador.favoritemovies.movielist.model.MovieListModel

class MovieListRepository(private val context: Context) {
    fun getMovieLists(callback: (movieLists: MutableList<MovieListModel>) -> Unit) {
        callback.invoke(
            mutableListOf<MovieListModel>(
                MovieListModel(
                    "Lista 1",
                    10
                ),
                MovieListModel(
                    "Lista 2",
                    20
                ),
                MovieListModel(
                    "Lista 3",
                    13
                ),
                MovieListModel(
                    "Lista 4",
                    8
                ),
                MovieListModel(
                    "Lista 5",
                    10
                ),
            )
        )
    }
}
