package com.gabrielcamargo.projetointegrador.moviedetails.details.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.gabrielcamargo.projetointegrador.moviedetails.details.repository.MovieDetailsRepository
import com.gabrielcamargo.projetointegrador.utils.movies.model.MovieModel
import kotlinx.coroutines.Dispatchers

class MovieDetailsViewModel(
    private val repository: MovieDetailsRepository
): ViewModel()  {
    private var _movieDetails: MovieModel? = null

    fun getMovieDetails(id: Int) = liveData(Dispatchers.IO) {
        val response = repository.getMovieDetails(id)

        _movieDetails = response
        emit(response)
    }

    class MovieDetailsViewModelFactory(
        private val repository: MovieDetailsRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieDetailsViewModel(repository) as T
        }
    }
}