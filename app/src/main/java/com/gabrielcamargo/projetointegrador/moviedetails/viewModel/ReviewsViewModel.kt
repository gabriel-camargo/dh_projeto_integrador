package com.gabrielcamargo.projetointegrador.moviedetails.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabrielcamargo.projetointegrador.moviedetails.model.ReviewModel
import com.gabrielcamargo.projetointegrador.moviedetails.repository.PhotosRepository
import com.gabrielcamargo.projetointegrador.moviedetails.repository.ReviewsRepository

class ReviewsViewModel(
    private val repository: ReviewsRepository
): ViewModel()  {
    val reviews = MutableLiveData<MutableList<ReviewModel>>()
    fun getReviews() {
        repository.getReviews {
            reviews.value = it
        }
    }

    class ReviewsViewModelFactory(
        private val repository: ReviewsRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ReviewsViewModel(repository) as T
        }
    }
}