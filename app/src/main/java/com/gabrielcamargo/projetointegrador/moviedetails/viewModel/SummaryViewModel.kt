package com.gabrielcamargo.projetointegrador.moviedetails.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabrielcamargo.projetointegrador.moviedetails.repository.PhotosRepository
import com.gabrielcamargo.projetointegrador.moviedetails.repository.SummaryRepository

class SummaryViewModel(
    private val repository: SummaryRepository
): ViewModel()  {
    val summary = MutableLiveData<String>()

    fun getSummary() {
        repository.getSummary {
            summary.value = it
        }
    }

    class SummaryViewModelFactory(
        private val repository: SummaryRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SummaryViewModel(repository) as T
        }
    }
}