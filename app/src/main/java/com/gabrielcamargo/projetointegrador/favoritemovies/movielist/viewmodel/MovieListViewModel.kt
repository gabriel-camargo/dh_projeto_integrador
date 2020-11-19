package com.gabrielcamargo.projetointegrador.favoritemovies.movielist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabrielcamargo.projetointegrador.favoritemovies.movielist.model.MovieListModel
import com.gabrielcamargo.projetointegrador.favoritemovies.movielist.repository.MovieListRepository

class MovieListViewModel(
    private val repository: MovieListRepository
): ViewModel() {
    val movieLists = MutableLiveData<MutableList<MovieListModel>>()

    fun getMovieLists() {
        repository.getMovieLists {
            movieLists.value = it
        }
    }

    @Suppress("UNCHECKED_CAST")
    class MovieListViewModelFactory(
        private val repository: MovieListRepository
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieListViewModel(repository) as T
        }

    }
}