package com.gabrielcamargo.projetointegrador.moviedetails.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabrielcamargo.projetointegrador.moviedetails.model.CastModel
import com.gabrielcamargo.projetointegrador.moviedetails.model.PhotoModel
import com.gabrielcamargo.projetointegrador.moviedetails.model.ReviewModel
import com.gabrielcamargo.projetointegrador.moviedetails.repository.MovieDetailsRepository

class MovieDetailsViewModel(
        private val repository: MovieDetailsRepository
): ViewModel() {
    val cast = MutableLiveData<MutableList<CastModel>>()
    val reviews = MutableLiveData<MutableList<ReviewModel>>()
    val photos = MutableLiveData<MutableList<PhotoModel>>()
    val summary = MutableLiveData<String>()


    fun getCast() {
        repository.getCast {
            cast.value = it
        }
    }

    fun getReviews() {
        repository.getReviews {
            reviews.value = it
        }
    }

    fun getPhotos() {
        repository.getPhotos {
            photos.value = it
        }
    }

    fun getSummary() {
        repository.getSummary {
            summary.value = it
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


