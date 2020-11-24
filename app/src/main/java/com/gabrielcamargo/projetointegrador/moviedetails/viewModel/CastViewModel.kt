package com.gabrielcamargo.projetointegrador.moviedetails.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabrielcamargo.projetointegrador.moviedetails.model.CastModel
import com.gabrielcamargo.projetointegrador.moviedetails.repository.CastRepository
import com.gabrielcamargo.projetointegrador.moviedetails.repository.PhotosRepository

class CastViewModel(
    private val repository: CastRepository
): ViewModel()  {

    val cast = MutableLiveData<MutableList<CastModel>>()
    fun getCast() {
        repository.getCast {
            cast.value = it
        }
    }

    class CastViewModelFactory(
        private val repository: CastRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CastViewModel(repository) as T
        }
    }
}