package com.gabrielcamargo.projetointegrador.moviedetails.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.model.MovieModel
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.repository.WatchlistRepository
import com.gabrielcamargo.projetointegrador.moviedetails.model.MovieDetailsModel
import com.gabrielcamargo.projetointegrador.moviedetails.repository.MovieDetailsRepository

class MovieDetailsViewModel(
        private val repository: MovieDetailsRepository
): ViewModel() {

    fun getDetails() {
        repository.getMovieDetails {
            val movieDetails = it
        }
    }

    class MovieDetailsViewModelFactory(
            private val repository: MovieDetailsRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieDetailsViewModel(repository) as T
        }
    }

}


