package com.gabrielcamargo.projetointegrador.search.viewmodel

import android.graphics.Movie
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.gabrielcamargo.projetointegrador.search.repository.SearchRepository
import com.gabrielcamargo.projetointegrador.utils.movies.model.MovieModel
import com.gabrielcamargo.projetointegrador.utils.moviesoffline.model.MovieModelOffline
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher

class SearchViewModel(
    private val repository: SearchRepository
) : ViewModel() {

    var movies: List<MovieModel> = listOf()
    private var moviesBeforeSearch = listOf<MovieModel>()

    fun getMovies(query: String = "", page: Int = 1) = liveData(Dispatchers.IO) {
        if (query.isEmpty()) {
            emit(listOf<MovieModel>())
        } else {
            val response = repository.searchMovies(query, page)
            movies = response.results

            emit(response.results)
        }
    }

    fun search(query: String? = null, page: Int = 1) = liveData(Dispatchers.IO) {
        moviesBeforeSearch = movies

        val response = repository.searchMovies(query.toString(), page)
        emit(response.results)
    }

    fun oldMovieList() = moviesBeforeSearch

    class SearchViewModelFactory(
        private val repository: SearchRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(repository) as T
        }

    }
}