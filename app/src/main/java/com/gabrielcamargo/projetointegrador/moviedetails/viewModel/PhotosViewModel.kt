package com.gabrielcamargo.projetointegrador.moviedetails.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.gabrielcamargo.projetointegrador.moviedetails.model.PhotoModel
import com.gabrielcamargo.projetointegrador.moviedetails.repository.PhotosRepository
import com.gabrielcamargo.projetointegrador.utils.movies.model.MovieModel
import kotlinx.coroutines.Dispatchers

class PhotosViewModel(
    private val repository: PhotosRepository
): ViewModel()  {
    private var _photo: PhotoModel? = null

    fun getPhotos(id: Int) = liveData(Dispatchers.IO) {
        val response = repository.getPhotos(id)

        _photo = response
        emit(response)
    }

//    val photos = MutableLiveData<MutableList<PhotoModel>>()
//    fun getPhotos() {
//        repository.getPhotos {
//            photos.value = it
//        }
//    }


    class PhotosViewModelFactory(
        private val repository: PhotosRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PhotosViewModel(repository) as T
        }
    }
}