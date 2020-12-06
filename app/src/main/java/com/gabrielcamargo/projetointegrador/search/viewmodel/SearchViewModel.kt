package com.gabrielcamargo.projetointegrador.search.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabrielcamargo.projetointegrador.search.repository.SearchRepository
import com.gabrielcamargo.projetointegrador.utils.moviesoffline.model.MovieModelOffline

class SearchViewModel(
    private val repository: SearchRepository
): ViewModel() {
    val movies = MutableLiveData<MutableList<MovieModelOffline>>()

    fun getMovies() {
        repository.getMovies {
            movies.value = it
        }
    }

    class SearchViewModelFactory(
        private val repository: SearchRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(repository) as T
        }

    }
}