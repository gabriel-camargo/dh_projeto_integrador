package com.gabrielcamargo.projetointegrador.moviedetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.gabrielcamargo.projetointegrador.moviedetails.model.ReviewModel
import com.gabrielcamargo.projetointegrador.moviedetails.repository.ReviewsRepository
import kotlinx.coroutines.Dispatchers

class ReviewsViewModel(
    private val repository: ReviewsRepository
): ViewModel()  {
    private var _photo: List<ReviewModel>? = null

    fun getReviews(id: Int) = liveData(Dispatchers.IO) {
        val response = repository.getReviews(id)
        val response2 = repository.getReviews2(id)

        _photo = response.results
        emit(response.results)
    }

    class ReviewsViewModelFactory(
        private val repository: ReviewsRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ReviewsViewModel(repository) as T
        }
    }
}