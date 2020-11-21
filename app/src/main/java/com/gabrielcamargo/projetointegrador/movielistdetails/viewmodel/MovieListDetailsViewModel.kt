package com.gabrielcamargo.projetointegrador.movielistdetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.model.MovieModel
import com.gabrielcamargo.projetointegrador.movielistdetails.repository.MovieListDetailsRepository

class MovieListDetailsViewModel(
    private val repository: MovieListDetailsRepository
) : ViewModel() {
    val movies = MutableLiveData<MutableList<MovieModel>>()

    fun getMovies() {
        repository.getMovies {
            movies.value = it
        }
    }

    class MovieListDetailsViewModelFactory(
        private val repository: MovieListDetailsRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieListDetailsViewModel(repository) as T
        }
    }
}