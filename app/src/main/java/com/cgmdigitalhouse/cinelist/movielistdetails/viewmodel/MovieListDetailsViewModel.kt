package com.cgmdigitalhouse.cinelist.movielistdetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cgmdigitalhouse.cinelist.movielistdetails.repository.MovieListDetailsRepository
import com.cgmdigitalhouse.cinelist.utils.moviesoffline.model.MovieModelOffline

class MovieListDetailsViewModel(
    private val repository: MovieListDetailsRepository
) : ViewModel() {
    val movies = MutableLiveData<MutableList<MovieModelOffline>>()

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