package com.gabrielcamargo.projetointegrador.moviedetails.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabrielcamargo.projetointegrador.moviedetails.model.CastModel
import com.gabrielcamargo.projetointegrador.moviedetails.model.MovieDetailsModel
import com.gabrielcamargo.projetointegrador.moviedetails.repository.CastRepository
import com.gabrielcamargo.projetointegrador.moviedetails.repository.MovieDetailsRepository

class MovieDetailsViewModel(
    private val repository: MovieDetailsRepository
): ViewModel()  {

    val movieDetails = MutableLiveData<MovieDetailsModel>()
    fun getMovieDetails() {
        repository.getMovieDetails {
            movieDetails.value = it
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