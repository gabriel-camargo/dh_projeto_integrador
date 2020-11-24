package com.gabrielcamargo.projetointegrador.moviedetails.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabrielcamargo.projetointegrador.moviedetails.model.PhotoModel
import com.gabrielcamargo.projetointegrador.moviedetails.repository.PhotosRepository

class PhotosViewModel(
    private val repository: PhotosRepository
): ViewModel()  {

    val photos = MutableLiveData<MutableList<PhotoModel>>()
    fun getPhotos() {
        repository.getPhotos {
            photos.value = it
        }
    }


    class PhotosViewModelFactory(
        private val repository: PhotosRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PhotosViewModel(repository) as T
        }
    }
}