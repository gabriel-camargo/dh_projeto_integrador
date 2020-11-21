package com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.model.MovieModel
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.repository.WatchlistRepository

class WatchlistViewModel (
        private val repository: WatchlistRepository
): ViewModel() {
    val movies = MutableLiveData<MutableList<MovieModel>>()

    fun getMovies() {
        repository.getWatchlistMovies {
            movies.value = it
        }
    }

    class WatchlistViewModelFactory(
            private val repository: WatchlistRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return WatchlistViewModel(repository) as T
        }

    }
}