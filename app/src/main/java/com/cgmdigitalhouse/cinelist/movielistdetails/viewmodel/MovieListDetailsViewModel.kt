package com.cgmdigitalhouse.cinelist.movielistdetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.cgmdigitalhouse.cinelist.movielistdetails.repository.MovieListDetailsRepository
import com.cgmdigitalhouse.cinelist.utils.moviesoffline.model.MovieModelOffline
import kotlinx.coroutines.Dispatchers

class MovieListDetailsViewModel(
    private val repository: MovieListDetailsRepository
) : ViewModel() {

    fun getListMoviesCrossRefEntity(id:Long)= liveData(Dispatchers.IO) {
        emit(repository.getListMoviesCrossRefEntity(id))
    }

    class MovieListDetailsViewModelFactory(
        private val repository: MovieListDetailsRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieListDetailsViewModel(repository) as T
        }
    }
}