package com.gabrielcamargo.projetointegrador.moviedetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.gabrielcamargo.projetointegrador.moviedetails.repository.MovieDetailsRepository
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

//    val movieDetails = MutableLiveData<MovieDetailsModel>()
//    fun getMovieDetails() {
//        repository.getMovieDetails {
//            movieDetails.value = it
//        }
//    }

    class MovieDetailsViewModelFactory(
        private val repository: MovieDetailsRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieDetailsViewModel(repository) as T
        }
    }
}